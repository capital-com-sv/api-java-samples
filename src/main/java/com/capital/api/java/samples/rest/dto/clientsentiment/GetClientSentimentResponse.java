package com.capital.api.java.samples.rest.dto.clientsentiment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientSentimentResponse {
    private String marketId;
    private Float longPositionPercentage;
    private Float shortPositionPercentage;
}
