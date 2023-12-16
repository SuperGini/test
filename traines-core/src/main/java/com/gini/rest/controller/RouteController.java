package com.gini.rest.controller;

import com.gini.rest.dto.request.RouteRequest;
import com.gini.rest.dto.request.RouteRequestUpdate;
import com.gini.rest.dto.response.RouteResponse;
import com.gini.service.RouteService;
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
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/route")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoute(@RequestBody RouteRequest routeRequest){
        routeService.createRoute(routeRequest);
    }

    @PutMapping("/route")
    public ResponseEntity<RouteResponse> updateRoute (@RequestBody RouteRequestUpdate routeRequestUpdate){
        var response = routeService.updateRoute(routeRequestUpdate);
        return ResponseEntity.ok(response);
    }


}
