package com.capital.api.java.samples.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private Status status;
    private String destination;
    private String correlationId;
    private Object payload;

    enum Status {
        OK, ERROR
    }
}
