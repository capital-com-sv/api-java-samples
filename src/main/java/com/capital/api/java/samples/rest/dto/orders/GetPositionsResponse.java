package com.capital.api.java.samples.rest.dto.orders;

import com.capital.api.java.samples.rest.dto.positions.PositionItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPositionsResponse {
    private List<PositionItem> positions;
}
