package com.gini.rest.dto.request;

import java.math.BigDecimal;

public record RouteRequest(
        String id,
        String startLocation,
        String endLocation,
        String price
) {
}
