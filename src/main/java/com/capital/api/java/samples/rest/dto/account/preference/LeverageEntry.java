package com.capital.api.java.samples.rest.dto.account.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeverageEntry {
    private List<Integer> available;
    private int current;
}
