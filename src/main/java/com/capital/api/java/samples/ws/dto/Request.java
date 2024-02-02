package com.capital.api.java.samples.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    private String destination;
    private String correlationId;
    private String cst;
    private String securityToken;
    private Object payload;
}
