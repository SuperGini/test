package com.gini.rest.dto.response;

import lombok.Builder;

@Builder
public record RouteResponse(
        String id,
        String startLocation,
        String endLocation,
        String price
) {
}
