package com.capital.api.java.samples.rest.dto.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSessionResponse {

    private AccountInfo accountInfo;
    private AccountType accountType;
    private List<AccountItem> accounts;
    private String clientId;
    private String currencyIsoCode;
    private String currencySymbol;
    private String currentAccountId;
    private boolean hasActiveDemoAccounts;
    private boolean hasActiveLiveAccounts;
    private String streamingHost;
    private int timezoneOffset;
    private Boolean trailingStopsEnabled;

}
