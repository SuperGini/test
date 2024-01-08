package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import com.gini.mapper.RouteMapper;
import gini.trainsapi.model.RouteRequest;
import gini.trainsapi.model.RouteRequestUpdate;
import gini.trainsapi.model.RouteResponsePaginated;
import gini.trainsapi.model.TicketResponsePaginated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

    public RouteResponsePaginated getAllRoutesPaginated(Integer pageNumber) {
        var route = trainsCoreGateway.getAllRoutesPaginated(pageNumber);

        var routes = route.getRouteResponses().stream()
                .map(routeMapper::mapFrom)
                .toList();

        return new RouteResponsePaginated()
                .routeResponses(routes)
                .totalRoutes(route.getTotalRoutes());
    }

    public RouteResponsePaginated getRoutesByDestination(Integer pageNumber, String destination) {
        log.debug("sending page number: {} and destination: {} to gateway", pageNumber, destination);
        var routesPaginated = trainsCoreGateway.getRoutesByDestination(pageNumber, destination);
        log.info("generate response for page number: {} and destination: {}", pageNumber, destination);
        return routeMapper.mapFrom(routesPaginated);
    }

}
