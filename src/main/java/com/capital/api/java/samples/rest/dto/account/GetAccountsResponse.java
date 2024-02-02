package com.capital.api.java.samples.rest.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAccountsResponse {
    private List<AccountItem> accounts;
}
