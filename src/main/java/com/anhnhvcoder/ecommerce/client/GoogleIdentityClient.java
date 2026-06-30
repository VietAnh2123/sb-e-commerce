package com.anhnhvcoder.ecommerce.client;

import com.anhnhvcoder.ecommerce.request.ExchangeTokenRequest;
import com.anhnhvcoder.ecommerce.response.ExchangeTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "google-identity", url = "https://oauth2.googleapis.com")
public interface GoogleIdentityClient {

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResponse exchangeToken(@QueryMap ExchangeTokenRequest request);

}
