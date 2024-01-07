package com.gini.gateway;


import gini.trainscore.model.RouteRequest;
import gini.trainscore.model.RouteRequestUpdate;
import gini.trainscore.model.TicketRequest;
import gini.trainscore.model.TicketResponsePaginated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface TrainsCoreGateway {

    @PostExchange("/ticket")
    void createTicket(@RequestBody TicketRequest ticketRequest);


    @PostExchange("/route")
    void createRoute(@RequestBody RouteRequest routeRequest);

    @PutExchange("/route")
    void updateRoute(@RequestBody RouteRequestUpdate routeRequestUpdate);

    @GetExchange("/tickets")
    TicketResponsePaginated getUsersTicketsPaginated(@RequestParam Integer pageNumber, @RequestParam String customerId);

    @GetExchange("/tickets/{pageNumber}")
    TicketResponsePaginated getAllTicketsPaginated(@PathVariable Integer pageNumber);

}
