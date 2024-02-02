package com.capital.api.java.samples.rest.dto.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSessionRequest {
    private String identifier;
    private String password;
    private Boolean encryptedPassword;
}
