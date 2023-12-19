package com.gini.service;

import com.gini.mapper.RouteMapper;
import com.gini.repository.RouteRepository;
import com.gini.rest.dto.response.RouteResponse;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteService {


    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;

    @Transactional
    public void createRoute(RouteRequest routeRequest) {
        log.debug("mapping request");
        var route = routeMapper.mapFrom(routeRequest);
        log.info("saving route: {} to database", route);
        routeRepository.save(route);
    }

    @Transactional
    public RouteResponse updateRoute(RouteRequestUpdate routeRequestUpdate) {
        log.debug("mapping route update");
        var route = routeMapper.mapFrom(routeRequestUpdate);
        log.info("updating route: {}", route);
        var updatedRoute = routeRepository.save(route);
        return routeMapper.mapFrom(updatedRoute);
    }


}
