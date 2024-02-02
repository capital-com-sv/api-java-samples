package com.capital.api.java.samples.rest.dto.market.navigation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeItem {
    private String id;
    private String name;
}
