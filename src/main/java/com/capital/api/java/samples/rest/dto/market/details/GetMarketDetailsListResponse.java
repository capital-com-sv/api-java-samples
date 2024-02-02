package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMarketDetailsListResponse {
    private List<GetMarketDetailsResponse> marketDetails;
}
