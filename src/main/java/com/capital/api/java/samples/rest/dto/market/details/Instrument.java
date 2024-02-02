package com.capital.api.java.samples.rest.dto.market.details;

import com.capital.api.java.samples.rest.dto.market.InstrumentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Instrument {
    private String epic;
    private String expiry;
    private String name;
    private Boolean forceOpenAllowed;
    private Boolean stopsLimitsAllowed;
    private Double lotSize;
    private InstrumentUnitType unit;
    private InstrumentType type;
    private Boolean guaranteedStopAllowed;
    private Boolean streamingPricesAvailable;
    private String marketId;
    private List<CurrenciesItem> currencies;
    private Integer sprintMarketsMinimumExpiryTime;
    private Integer sprintMarketsMaximumExpiryTime;
    private List<MarginDepositBandsItem> marginDepositBands;
    private BigDecimal margin;
    private SlippageFactor slippageFactor;
    private OpeningHours openingHours;
    private ExpiryDetails expiryDetails;
    private RolloverDetails rolloverDetails;
    private String newsCode;
    private String chartCode;
    private String country;
    private String valueOfOnePip;
    private String onePipMeans;
    private String contractSize;
    private List<String> specialInfo;
}
