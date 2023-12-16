package com.gini.mapper;

import com.gini.model.Route;
import com.gini.rest.dto.request.RouteRequest;
import com.gini.rest.dto.request.RouteRequestUpdate;
import com.gini.rest.dto.response.RouteResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RouteMapper {


    public Route mapFrom(RouteRequest routeRequest) {
           return Route.builder()
                   .price(new BigDecimal(routeRequest.price()))
                   .startLocation(routeRequest.startLocation())
                   .endLocation(routeRequest.endLocation())
                   .build();
    }

    public Route mapFrom(RouteRequestUpdate routeRequestUpdate) {
        return Route.builder()
                .id(routeRequestUpdate.id())
                .price(new BigDecimal(routeRequestUpdate.price()))
                .startLocation(routeRequestUpdate.startLocation())
                .endLocation(routeRequestUpdate.endLocation())
                .build();
    }

    public RouteResponse mapFrom(Route route) {
        return RouteResponse.builder()
                .id(route.getId())
                .price(route.getPrice().toString())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation())
                .build();



    }

}
