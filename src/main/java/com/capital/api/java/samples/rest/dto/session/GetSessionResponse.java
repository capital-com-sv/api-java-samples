package com.capital.api.java.samples.rest.dto.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSessionResponse {
    private String accountId;
    private String clientId;
    private String locale;
    private String currency;
    private String streamEndpoint;
    private int timezoneOffset;
}
