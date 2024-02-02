package com.capital.api.java.samples.rest.dto.prices;

import com.capital.api.java.samples.rest.dto.market.InstrumentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPricesResponse {
    private List<PricesItem> prices;
    private InstrumentType instrumentType;
    private Metadata metadata;
}
