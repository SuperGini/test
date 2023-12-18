package com.gini.service;

import com.gini.mapper.RouteMapper;
import com.gini.repository.RouteRepository;
import com.gini.rest.dto.response.RouteResponse;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RouteService {


    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;

    @Transactional
    public void createRoute(RouteRequest routeRequest) {
        var route = routeMapper.mapFrom(routeRequest);
        routeRepository.save(route);
    }

    @Transactional
    public RouteResponse updateRoute(RouteRequestUpdate routeRequestUpdate) {
        var route = routeMapper.mapFrom(routeRequestUpdate);
        var updatedRoute = routeRepository.save(route);
        return routeMapper.mapFrom(updatedRoute);
    }


}
