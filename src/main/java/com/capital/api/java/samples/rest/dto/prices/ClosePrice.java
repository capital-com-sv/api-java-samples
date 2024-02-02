package com.capital.api.java.samples.rest.dto.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClosePrice {
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal lastTraded;
}
