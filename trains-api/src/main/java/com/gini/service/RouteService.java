package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final TrainsCoreGateway trainsCoreGateway;

    public void createRoute(RouteRequest routeRequest){
        trainsCoreGateway.createRoute(routeRequest);
    }

    public void updateRoute(RouteRequestUpdate routeRequestUpdate){
        trainsCoreGateway.updateRoute(routeRequestUpdate);
    }

}
