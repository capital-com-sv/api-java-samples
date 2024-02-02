package com.capital.api.java.samples.rest.dto.orders.confimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AffectedDealsItem {
    private String dealId;
    private AffectedDealStatus status;
}
