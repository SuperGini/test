package com.gini.mapper;

import gini.trainsapi.model.RouteResponse;
import gini.trainsapi.model.RouteResponsePaginated;
import gini.trainscore.model.RouteRequest;
import gini.trainscore.model.RouteRequestUpdate;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {

    public RouteRequest mapFrom(gini.trainsapi.model.RouteRequest request) {
        return new RouteRequest()
                .id(request.getId())
                .price(request.getPrice())
                .startLocation(request.getStartLocation())
                .endLocation(request.getEndLocation());
    }

    public RouteRequestUpdate mapFrom(gini.trainsapi.model.RouteRequestUpdate request) {
        return new RouteRequestUpdate()
                .id(request.getId())
                .price(request.getPrice())
                .startLocation(request.getStartLocation())
                .endLocation(request.getEndLocation());
    }

    public RouteResponse mapFrom(gini.trainscore.model.RouteResponse route) {
        return new RouteResponse()
                .id(route.getId())
                .price(route.getPrice())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation());
    }

    public RouteResponsePaginated mapFrom(gini.trainscore.model.RouteResponsePaginated route) {
        var routes = route.getRouteResponses().stream()
                .map(this::mapFrom)
                .toList();

        return new RouteResponsePaginated()
                .totalRoutes(route.getTotalRoutes())
                .routeResponses(routes);
    }


}
