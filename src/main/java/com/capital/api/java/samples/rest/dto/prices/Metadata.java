package com.capital.api.java.samples.rest.dto.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
    private Allowance allowance;
}
