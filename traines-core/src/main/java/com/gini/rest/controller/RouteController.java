package com.gini.rest.controller;

import com.gini.service.RouteService;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import gin.model.RouteResponsePaginated;
import gini.api.RouteApi;
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
        log.info("request for routes of page: {}, received", pageNumber);
        var routes = routeService.getAllRoutesPaginated(pageNumber);
        return ResponseEntity.ok(routes);
    }

    @Override
    public ResponseEntity<RouteResponsePaginated> getRoutesByDestination(Integer pageNumber, String destination) {
        var routes = routeService.getRoutesByDestination(pageNumber, destination);
        return ResponseEntity.ok(routes);
    }

    @Override
    public ResponseEntity<Void> updateRoute(RouteRequestUpdate routeRequestUpdate) {
        log.debug("route update received");
        routeService.updateRoute(routeRequestUpdate);
        return ResponseEntity.ok().build();
    }
}
