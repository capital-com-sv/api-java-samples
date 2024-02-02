package com.capital.api.java.samples.rest.dto.account.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePreferencesResponse {
    private String status;
}
