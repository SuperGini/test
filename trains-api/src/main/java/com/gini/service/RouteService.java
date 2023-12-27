package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import com.gini.mapper.RouteMapper;
import gini.trainsapi.model.RouteRequest;
import gini.trainsapi.model.RouteRequestUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final TrainsCoreGateway trainsCoreGateway;
    private final RouteMapper routeMapper;

    public void createRoute(RouteRequest routeRequest) {
        var request = routeMapper.mapFrom(routeRequest);
        trainsCoreGateway.createRoute(request);
    }

    public void updateRoute(RouteRequestUpdate routeRequestUpdate) {
        var request = routeMapper.mapFrom(routeRequestUpdate);
        trainsCoreGateway.updateRoute(request);
    }

}
