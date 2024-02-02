package com.capital.api.java.samples.rest.dto.market.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/** Market schedule */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHours {
    private final List<String> mon = new ArrayList<>();
    private final List<String> tue = new ArrayList<>();
    private final List<String> wed = new ArrayList<>();
    private final List<String> thu = new ArrayList<>();
    private final List<String> fri = new ArrayList<>();
    private final List<String> sat = new ArrayList<>();
    private final List<String> sun = new ArrayList<>();
}