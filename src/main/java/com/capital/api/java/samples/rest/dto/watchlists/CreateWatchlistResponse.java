package com.capital.api.java.samples.rest.dto.watchlists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWatchlistResponse {
    private String watchlistId;
    private Status status;
}
