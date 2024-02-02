package com.capital.api.java.samples.rest.dto.positions;

import com.capital.api.java.samples.rest.dto.orders.Direction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreatePositionRequest {
    private Direction direction;
    private String epic;
    private Boolean guaranteedStop;
    private Boolean trailingStop;
    private BigDecimal profitAmount;
    private BigDecimal profitLevel;
    private BigDecimal profitDistance;
    private BigDecimal size;
    private BigDecimal stopAmount;
    private BigDecimal stopDistance;
    private BigDecimal stopLevel;
}
