package com.gini.controller;

import com.gini.service.RouteService;
import gini.trainsapi.api.RouteApi;
import gini.trainsapi.model.RouteRequest;
import gini.trainsapi.model.RouteRequestUpdate;
import gini.trainsapi.model.RouteResponsePaginated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RouteController implements RouteApi {

    private final RouteService routeService;

    @Override
    public ResponseEntity<Void> createRoute(RouteRequest routeRequest) {
        log.debug("route request received");
        routeService.createRoute(routeRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RouteResponsePaginated> getAllRoutesPaginated(Integer pageNumber) {
        var route =  routeService.getAllRoutesPaginated(pageNumber);
        return ResponseEntity.ok(route);
    }

    @Override
    public ResponseEntity<RouteResponsePaginated> getRoutesByDestination(Integer pageNumber, String destination) {
        var route = routeService.getRoutesByDestination(pageNumber, destination);
        return ResponseEntity.ok(route);

    }

    @Override
    public ResponseEntity<Void> updateRoute(RouteRequestUpdate routeRequestUpdate) {
        log.debug("route update received");
        routeService.updateRoute(routeRequestUpdate);
        return ResponseEntity.ok().build();
    }
}
