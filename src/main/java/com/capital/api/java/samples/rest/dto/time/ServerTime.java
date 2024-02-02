package com.capital.api.java.samples.rest.dto.time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerTime {
    private long serverTime;
}
