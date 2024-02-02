package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpiryDetails {
    private String lastDealingDate;
    private String settlementInfo;
}
