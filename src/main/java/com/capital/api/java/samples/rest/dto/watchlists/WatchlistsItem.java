package com.capital.api.java.samples.rest.dto.watchlists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchlistsItem {
    private String id;
    private String name;
    private Boolean editable;
    private Boolean deleteable;
    private Boolean defaultSystemWatchlist;
}
