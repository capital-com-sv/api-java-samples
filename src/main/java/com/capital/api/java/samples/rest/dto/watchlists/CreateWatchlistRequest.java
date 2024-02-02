package com.capital.api.java.samples.rest.dto.watchlists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWatchlistRequest {
    private String name;
    private Set<String> epics;
}
