package com.capital.api.java.samples.rest.dto.positions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePositionResponse {
    private String dealReference;
}
