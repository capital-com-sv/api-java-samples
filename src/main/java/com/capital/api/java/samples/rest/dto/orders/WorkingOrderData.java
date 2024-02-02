package com.capital.api.java.samples.rest.dto.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingOrderData {
    private String createdDate;
    private String createdDateUTC;
    private String currencyCode;
    private String dealId;
    private Direction direction;
    private String epic;
    private Boolean dma;
    private String goodTillDate;
    private String goodTillDateUTC;
    private Boolean guaranteedStop;
    private BigDecimal profitDistance;
    private BigDecimal orderLevel;
    private BigDecimal orderSize;
    private OrderType orderType;
    private BigDecimal stopDistance;
    private TimeInForce timeInForce;
}
