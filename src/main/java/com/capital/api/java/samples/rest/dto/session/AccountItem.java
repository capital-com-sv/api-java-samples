package com.capital.api.java.samples.rest.dto.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountItem {
    private String accountId;
    private String accountName;
    private Boolean preferred;
    private AccountType accountType;
}
