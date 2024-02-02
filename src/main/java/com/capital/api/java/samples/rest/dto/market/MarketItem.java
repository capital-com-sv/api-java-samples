package com.capital.api.java.samples.rest.dto.market;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketItem {
    private Integer delayTime;
    private String epic;
    private Float netChange;
    private Integer lotSize;
    private String expiry;
    private InstrumentType instrumentType;
    private String instrumentName;
    private Float high;
    private Float low;
    private Float percentageChange;
    private String updateTime;
    private String updateTimeUTC;
    private Float bid;
    private Float offer;
    private Boolean otcTradeable;
    private Boolean streamingPricesAvailable;
    private MarketStatus marketStatus;
    private Integer scalingFactor;
}
