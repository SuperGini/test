package com.gini.rest.controller;

import com.gini.rest.dto.request.RouteRequest;
import com.gini.rest.dto.request.RouteRequestUpdate;
import com.gini.rest.dto.response.RouteResponse;
import com.gini.service.RouteService;
import gini.api.RouteApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController implements RouteApi {

    private final RouteService routeService;

    @Override
    public ResponseEntity<Void> createRoute(gin.model.RouteRequest routeRequest) {
        routeService.createRoute(routeRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateRoute(gin.model.RouteRequestUpdate routeRequestUpdate) {
        var response = routeService.updateRoute(routeRequestUpdate);
        return ResponseEntity.ok().build();
    }
}
