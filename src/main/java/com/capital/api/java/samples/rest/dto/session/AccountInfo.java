package com.capital.api.java.samples.rest.dto.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInfo {
    private Float balance;
    private Float deposit;
    private Float profitLoss;
    private Float available;

}
