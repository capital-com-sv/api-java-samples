package com.capital.api.java.samples.service;

import com.capital.api.java.samples.util.AlgorithmUtil;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@RequiredArgsConstructor
public class RSI {
    /**
     * map with symbol and (map with timeframe and (ringBuffer with price))
     */
    private Map<String, Map<String, CircularFifoQueue<Double>>> rsiBuffer = new ConcurrentHashMap<>();
    private Map<String, Map<String, Double>> rsiValues = new ConcurrentHashMap<>();
    @NonNull
    private Integer period;

    public void putQuote(String symbol, String timeframe, Double value) {
        addToCache(List.of(symbol), List.of(timeframe));
        rsiBuffer.get(symbol).get(timeframe).add(value);
        computeRSIIfPossible(symbol, timeframe);
    }

    public Double putQuoteAndGetRSI(String symbol, String timeframe, Double value) {
        addToCache(List.of(symbol), List.of(timeframe));
        rsiBuffer.get(symbol).get(timeframe).add(value);
        computeRSIIfPossible(symbol, timeframe);
        return rsiValues.get(symbol).get(timeframe);
    }

    public void addToCache(List<String> symbol, List<String> timeframe) {
        symbol.forEach(epic -> {
            rsiBuffer.computeIfAbsent(epic, key -> new ConcurrentHashMap<>());
            rsiValues.computeIfAbsent(epic, key -> new ConcurrentHashMap<>());
            timeframe.forEach(frame -> {
                rsiBuffer.get(epic).computeIfAbsent(frame, key -> new CircularFifoQueue<>(period + 1));
                rsiValues.get(epic).computeIfAbsent(frame, key -> Double.NaN);
            });
        });
    }

    private void computeRSIIfPossible(String symbol, String timeframe) {
        if (!rsiBuffer.get(symbol).get(timeframe).isAtFullCapacity()) {
            return;
        }
        rsiValues.get(symbol).put(timeframe,
                AlgorithmUtil.rsi(rsiBuffer.get(symbol).get(timeframe)
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .toArray(), period)[period]);
    }
}
