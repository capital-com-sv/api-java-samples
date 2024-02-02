package com.capital.api.java.samples.rest.dto.watchlists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWatchlistsResponse {
    private List<WatchlistsItem> watchlists;
}
