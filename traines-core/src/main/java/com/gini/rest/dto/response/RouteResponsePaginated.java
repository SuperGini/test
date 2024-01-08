package com.gini.rest.dto.response;

import java.util.List;

public record RouteResponsePaginated(
        Long totalRoutes,
        List<RouteResponse> routeResponses
) {
}
