package com.gini.mapper;

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


}
