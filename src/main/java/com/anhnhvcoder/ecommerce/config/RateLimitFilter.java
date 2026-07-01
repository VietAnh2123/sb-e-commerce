package com.anhnhvcoder.ecommerce.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RateLimitFilter extends OncePerRequestFilter {

    private static final long STALE_BUCKET_NANOS = Duration.ofMinutes(15).toNanos();
    private static final int CLEANUP_INTERVAL = 1000;

    private final RateLimitProperties properties;
    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final AtomicInteger requestCounter = new AtomicInteger();

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return !properties.isEnabled()
                || HttpMethod.OPTIONS.matches(request.getMethod())
                || !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        cleanupStaleBuckets();

        String clientKey = getClientIp(request);
        TokenBucket bucket = buckets.computeIfAbsent(clientKey, ignored -> new TokenBucket(
                properties.getCapacity(),
                properties.getRefillTokens(),
                Duration.ofSeconds(properties.getRefillPeriodSeconds()).toNanos()
        ));

        if (!bucket.tryConsume()) {
            writeRateLimitResponse(response, bucket.getRetryAfterSeconds());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void writeRateLimitResponse(HttpServletResponse response, long retryAfterSeconds) throws IOException {
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(HttpHeaders.RETRY_AFTER, String.valueOf(retryAfterSeconds));
        response.getWriter().write("""
                {"message":"Too many requests. Please try again later."}
                """);
    }

    private String getClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) {
            return realIp.trim();
        }

        return request.getRemoteAddr();
    }

    private void cleanupStaleBuckets() {
        if (requestCounter.incrementAndGet() % CLEANUP_INTERVAL != 0) {
            return;
        }

        long now = System.nanoTime();
        Iterator<Map.Entry<String, TokenBucket>> iterator = buckets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, TokenBucket> entry = iterator.next();
            if (entry.getValue().isStale(now)) {
                iterator.remove();
            }
        }
    }

    private static class TokenBucket {

        private final int capacity;
        private final int refillTokens;
        private final long refillPeriodNanos;
        private int tokens;
        private long lastRefillNanos;
        private long lastSeenNanos;

        TokenBucket(int capacity, int refillTokens, long refillPeriodNanos) {
            this.capacity = capacity;
            this.refillTokens = refillTokens;
            this.refillPeriodNanos = refillPeriodNanos;
            this.tokens = capacity;
            this.lastRefillNanos = System.nanoTime();
            this.lastSeenNanos = this.lastRefillNanos;
        }

        synchronized boolean tryConsume() {
            refill();
            lastSeenNanos = System.nanoTime();

            if (tokens <= 0) {
                return false;
            }

            tokens--;
            return true;
        }

        synchronized long getRetryAfterSeconds() {
            refill();
            if (tokens > 0) {
                return 0;
            }

            long nanosUntilRefill = refillPeriodNanos - (System.nanoTime() - lastRefillNanos);
            return Math.max(1, Duration.ofNanos(Math.max(0, nanosUntilRefill)).toSeconds());
        }

        synchronized boolean isStale(long now) {
            return now - lastSeenNanos > STALE_BUCKET_NANOS;
        }

        private void refill() {
            long now = System.nanoTime();
            long periods = (now - lastRefillNanos) / refillPeriodNanos;
            if (periods <= 0) {
                return;
            }

            long newTokens = periods * refillTokens;
            tokens = (int) Math.min(capacity, tokens + newTokens);
            lastRefillNanos += periods * refillPeriodNanos;
        }
    }
}
