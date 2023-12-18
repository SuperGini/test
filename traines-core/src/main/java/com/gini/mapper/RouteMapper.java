package com.gini.mapper;

import com.gini.model.Route;
import com.gini.rest.dto.response.RouteResponse;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RouteMapper {


    public Route mapFrom(RouteRequest routeRequest) {
           return Route.builder()
                   .price(new BigDecimal(routeRequest.getPrice()))
                   .startLocation(routeRequest.getStartLocation())
                   .endLocation(routeRequest.getEndLocation())
                   .build();
    }

    public Route mapFrom(RouteRequestUpdate routeRequestUpdate) {
        return Route.builder()
                .id(routeRequestUpdate.getId())
                .price(new BigDecimal(routeRequestUpdate.getPrice()))
                .startLocation(routeRequestUpdate.getStartLocation())
                .endLocation(routeRequestUpdate.getEndLocation())
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
