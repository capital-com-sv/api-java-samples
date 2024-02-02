package com.capital.api.java.samples.rest.dto.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Allowance {
    private Integer remainingAllowance;
    private Integer totalAllowance;
    private Integer allowanceExpiry;
}
