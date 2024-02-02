package com.capital.api.java.samples.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "capital.user")
public class CapitalUserConfig {
    private String login;
    private String password;
    private String apiKey;
    private Enviroment env;
}
