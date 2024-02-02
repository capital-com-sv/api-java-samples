package com.capital.api.java.samples.rest.dto.account.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateActiveAccountResponse {
    private boolean dealingEnabled;
    private boolean hasActiveDemoAccounts;
    private boolean hasActiveLiveAccounts;
    private boolean trailingStopsEnabled;
}
