package com.capital.api.java.samples.rest.dto.account.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePreferencesRequest {
    private boolean hedgingMode;
    private Map<LeverageGroup, Integer> leverages;
}
