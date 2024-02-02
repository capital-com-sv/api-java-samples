package com.capital.api.java.samples.rest.dto.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWorkingOrdersResponse {
    private List<WorkingOrdersItem> workingOrders;
}
