package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginDepositBandsItem {
    private Integer min;
    private Integer max;
    private BigDecimal margin;
}
