package com.capital.api.java.samples.rest.dto.positions;

import com.capital.api.java.samples.rest.dto.orders.MarketData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionItem {
    private Position position;
    private MarketData market;
}
