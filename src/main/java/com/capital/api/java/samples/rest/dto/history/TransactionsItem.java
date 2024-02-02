package com.capital.api.java.samples.rest.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsItem {
    private String currency;
    private String date;
    private String dateUtc;
    private String instrumentName;
    private String reference;
    private String size;
    private String transactionType;
}
