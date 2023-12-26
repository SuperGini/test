package com.gini.gateway;


import gin.model.RouteRequest;
import gin.model.RouteRequestUpdate;
import gin.model.TicketRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface TrainsCoreGateway {

    @PostExchange("/ticket")
    void createTicket(@RequestBody TicketRequest ticketRequest);


    @PostExchange("/route")
    void createRoute(@RequestBody RouteRequest routeRequest);


    @PutExchange("/route")
    void updateRoute(@RequestBody RouteRequestUpdate routeRequestUpdate);

}
