package com.capital.api.java.samples.rest.util;

import com.capital.api.java.samples.rest.dto.orders.Direction;
import com.capital.api.java.samples.rest.dto.orders.CreateWorkingOrderRequest;
import com.capital.api.java.samples.rest.dto.orders.Type;
import com.capital.api.java.samples.rest.dto.positions.CreatePositionRequest;

import java.math.BigDecimal;

public final class DtoGenerator {

    public static CreatePositionRequest createPosition() {
        CreatePositionRequest request = CreatePositionRequest.builder().build();
        request.setEpic("SILVER");
        request.setSize(BigDecimal.ONE);
        request.setDirection(Direction.BUY);
        return request;
    }

    public static CreateWorkingOrderRequest createOrder() {
        CreateWorkingOrderRequest request = new CreateWorkingOrderRequest();
        request.setDirection(Direction.BUY);
        request.setEpic("NATURALGAS");
        request.setLevel(new BigDecimal(1));
        request.setSize(new BigDecimal(1));
        request.setGuaranteedStop(false);
        request.setType(Type.LIMIT);
        return request;
    }
}
