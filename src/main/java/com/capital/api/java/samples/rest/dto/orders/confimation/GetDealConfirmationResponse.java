package com.capital.api.java.samples.rest.dto.orders.confimation;

import com.capital.api.java.samples.rest.dto.orders.Direction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDealConfirmationResponse {
    private PositionStatus status;
    private Reason reason;
    private DealStatus dealStatus;
    private String epic;
    private String expiry;
    private String dealReference;
    private String dealId;
    private List<AffectedDealsItem> affectedDeals;
    private Float level;
    private Float size;
    private Direction direction;
    private Float stopLevel;
    private Float profitLevel;
    private Float stopDistance;
    private Float profitDistance;
    private Boolean guaranteedStop;
    private Boolean trailingStop;
    private BigDecimal profit;
    private String profitCurrency;
}
