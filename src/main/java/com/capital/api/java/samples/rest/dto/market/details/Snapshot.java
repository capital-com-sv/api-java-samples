package com.capital.api.java.samples.rest.dto.market.details;

import com.capital.api.java.samples.rest.dto.market.MarketStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snapshot {
    private MarketStatus marketStatus;
    private BigDecimal netChange;
    private BigDecimal percentageChange;
    private String updateTime;
    private Integer delayTime;
    private BigDecimal bid;
    private BigDecimal offer;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal binaryOdds;
    private Integer decimalPlacesFactor;
    private Integer scalingFactor;
    private BigDecimal controlledRiskExtraSpread;
}
