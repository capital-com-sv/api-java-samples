package com.capital.api.java.samples.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "settings")
public class SettingsConfig {
    private List<String> epics;
    private List<String> resolutions;
    private Integer quantityPresents;
    private Integer quantityManual;
    private Integer sltpPresents;
    private Integer RSIPeriod;
    private String finacc;
    private Integer sltpPipsFromLowestPrice;
    private Boolean sltpUsingPips;
    private Boolean quantityUsingManual;
}
