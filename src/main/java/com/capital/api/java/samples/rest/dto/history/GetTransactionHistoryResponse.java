package com.capital.api.java.samples.rest.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionHistoryResponse {
    private List<TransactionsItem> transactions;
}
