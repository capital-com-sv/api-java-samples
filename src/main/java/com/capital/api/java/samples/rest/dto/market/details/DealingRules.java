package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealingRules {
    private MinStepDistance minStepDistance;
    private MinDealSize minDealSize;
    private MinGuaranteedStopDistance minGuaranteedStopDistance;
    private MinStopOrProfitDistance minStopOrProfitDistance;
    private MaxStopOrProfitDistance maxStopOrProfitDistance;
    private MarketOrderPreference marketOrderPreference;
}
