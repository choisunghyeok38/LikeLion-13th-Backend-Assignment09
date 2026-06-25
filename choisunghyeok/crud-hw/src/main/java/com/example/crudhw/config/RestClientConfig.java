package com.example.crudhw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${kosis.api.base-url}")
    private String kosisBaseUrl;

    @Bean
    public RestClient kosisRestClient() {
        return RestClient.builder()
                .baseUrl(kosisBaseUrl)
                .build();
    }
}
