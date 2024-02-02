package com.capital.api.java.samples.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffsetPair {
    private BigDecimal minPriceOffset;
    private BigDecimal maxPriceOffset;
}
