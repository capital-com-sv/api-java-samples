package com.capital.api.java.samples.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    String demoURL(@Value("${capital.api.demo.domain.URL}") String url) {
        return url;
    }

    @Bean
    String prodURL(@Value("${capital.api.domain.URL}") String url) {
        return url;
    }
}
