package com.capital.api.java.samples.rest.dto.orders;

import com.capital.api.java.samples.rest.dto.market.InstrumentType;
import com.capital.api.java.samples.rest.dto.market.MarketStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketData {
    private String instrumentName;
    private String expiry;
    private String epic;
    private InstrumentType instrumentType;
    private Float lotSize;
    private Float high;
    private Float low;
    private Float percentageChange;
    private Float netChange;
    private Float bid;
    private Float offer;
    private String updateTime;
    private Integer delayTime;
    private Boolean streamingPricesAvailable;
    private MarketStatus marketStatus;
    private Integer scalingFactor;
}
