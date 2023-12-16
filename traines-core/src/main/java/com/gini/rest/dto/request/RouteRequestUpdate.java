package com.gini.rest.dto.request;

public record RouteRequestUpdate(
        String id,
        String startLocation,
        String endLocation,
        String price
) {
}
