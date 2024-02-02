package com.capital.api.java.samples.rest.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {
    private Float balance;
    private Float deposit;
    private Float profitLoss;
    private Float available;
}
