package com.capital.api.java.samples.rest.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetActivityHistoryResponse {
    private java.util.List<ActivitiesItem> activities;
}
