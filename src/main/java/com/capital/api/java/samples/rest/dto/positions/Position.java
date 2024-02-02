package com.capital.api.java.samples.rest.dto.positions;

import com.capital.api.java.samples.rest.dto.orders.Direction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {
    private Float contractSize;
    private String createdDate;
    private String dealId;
    private BigDecimal dealSize;
    private Direction direction;
    private BigDecimal profitLevel;
    private BigDecimal openLevel;
    private String currency;
    private Boolean guaranteedStop;
    private BigDecimal stopLevel;
    private BigDecimal trailingStep;
    private BigDecimal trailingStopDistance;
}
