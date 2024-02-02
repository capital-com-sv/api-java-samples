package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrenciesItem {
    private String code;
    private String name;
    private String symbol;
    private Float baseExchangeRate;
    private Float exchangeRate;
    private Boolean isDefault;
}
