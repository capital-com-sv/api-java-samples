package com.capital.api.java.samples.rest.dto.market.navigation;

import com.capital.api.java.samples.rest.dto.market.MarketItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMarketNavigationResponse {
    private List<NodeItem> nodes;
    private List<MarketItem> markets;
}
