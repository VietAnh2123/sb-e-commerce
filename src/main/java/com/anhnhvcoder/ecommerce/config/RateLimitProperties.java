package com.anhnhvcoder.ecommerce.config;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties(prefix = "rate-limit")
public class RateLimitProperties {

    private boolean enabled = true;

    @Min(1)
    private int capacity = 100;

    @Min(1)
    private int refillTokens = 100;

    @Min(1)
    private long refillPeriodSeconds = 60;
}
