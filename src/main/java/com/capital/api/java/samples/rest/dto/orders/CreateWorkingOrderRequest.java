package com.capital.api.java.samples.rest.dto.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWorkingOrderRequest {
    private String dealReference;
    private String epic;
    private String expiry;
    private Direction direction;
    private BigDecimal size;
    private BigDecimal level;
    private Type type;
    private String currencyCode;
    private TimeInForce timeInForce;
    private String goodTillDate;
    private Boolean guaranteedStop;
    private Boolean forceOpen;
    private BigDecimal stopLevel;
    private BigDecimal stopDistance;
    private BigDecimal profitLevel;
    private BigDecimal profitDistance;
}
