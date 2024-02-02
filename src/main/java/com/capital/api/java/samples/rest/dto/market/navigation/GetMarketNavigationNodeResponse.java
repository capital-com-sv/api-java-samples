package com.capital.api.java.samples.rest.dto.market.navigation;

import com.capital.api.java.samples.rest.dto.market.MarketItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMarketNavigationNodeResponse {
    private List<NodeItem> nodes;
    private List<MarketItem> markets;
}
