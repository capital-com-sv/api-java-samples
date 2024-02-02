package com.capital.api.java.samples.rest.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountItem {
    private String accountId;
    private String accountName;
    private AccountType accountType;
    private Balance balance;
    private String currency;
    private boolean preferred;
    private Status status;
}
