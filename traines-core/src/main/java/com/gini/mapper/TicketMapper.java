package com.gini.mapper;

import com.gini.model.Customer;
import com.gini.model.Route;
import com.gini.model.Ticket;
import com.gini.rest.dto.request.TicketRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TicketMapper {

    public Ticket mapFrom(TicketRequest ticketRequest) {
        var routeRequest = ticketRequest.route();
        var customerRequest = ticketRequest.customer();

        var route = Route.builder()
                .id(routeRequest.id())
                .startLocation(routeRequest.startLocation())
                .endLocation(routeRequest.endLocation())
                .build();

        var customer = Customer.builder()
                .id(customerRequest.id())
                .email(customerRequest.email())
                .build();

        return Ticket.builder()
                .price(new BigDecimal(ticketRequest.price()))
                .route(route)
                .customer(customer)
                .build()
                .mapCustomerId() //creating the relation to be saved in  db
                .mapRouteId();   //creating the relation to be saved in  db
    }


}
