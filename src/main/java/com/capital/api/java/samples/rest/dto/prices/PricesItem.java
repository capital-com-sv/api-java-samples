package com.capital.api.java.samples.rest.dto.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PricesItem {
    private String snapshotTime;
    private String snapshotTimeUTC;
    private OpenPrice openPrice;
    private ClosePrice closePrice;
    private HighPrice highPrice;
    private LowPrice lowPrice;
    private Long lastTradedVolume;
}
