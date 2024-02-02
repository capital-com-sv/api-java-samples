package com.capital.api.java.samples.ws.dto.market;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDataSubscribe {
    private List<String> epics;
    private List<String> resolutions;
}
