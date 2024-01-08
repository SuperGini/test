package com.gini.service;

import com.gini.mapper.RouteMapper;
import com.gini.repository.RouteRepository;
import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import gin.model.RouteResponse;
import gin.model.RouteResponsePaginated;
import gin.model.TicketResponsePaginated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public RouteResponsePaginated getAllRoutesPaginated(Integer pageNumber){
        var nrOfElementsOnPage = 5;
        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);

        var routesPage = routeRepository.getAllRoutes(page);

        Long totalRoutes = routesPage.getTotalElements();

        log.info("mapping route response for page: {}", pageNumber);
        var routeResponses = routesPage.stream()
                .map(routeMapper::mapFrom)
                .toList();
        return new RouteResponsePaginated()
                .routeResponses(routeResponses)
                .totalRoutes(totalRoutes);
    }

    @Transactional(readOnly = true)
    public RouteResponsePaginated getRoutesByDestination(Integer pageNumber, String destination) {
        var nrOfElementsOnPage = 5;
        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);

        var ticketsPage = routeRepository.getRoutesByDestination(page, destination);

        Long totalRoutes = ticketsPage.getTotalElements();

        log.info("mapping ticket response for destination: {}", destination);
        var routeResponses = ticketsPage.stream()
                .map(routeMapper::mapFrom)
                .toList();
        return new RouteResponsePaginated()
                .totalRoutes(totalRoutes)
                .routeResponses(routeResponses);

    }


}
