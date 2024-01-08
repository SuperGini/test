package com.gini.mapper;

import com.gini.model.Customer;
import com.gini.model.Route;
import com.gini.model.Ticket;
import gin.model.TicketRequest;
import gin.model.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public Ticket mapFrom(TicketRequest ticketRequest, Route route) {
        var customerRequest = ticketRequest.getCustomer();
        var customer = Customer.builder()
                .id(customerRequest.getId())
                .email(customerRequest.getEmail())
                .build();

        return Ticket.builder()
                .price(route.getPrice())
                .route(route)
                .customer(customer)
                .build();

    }

    public Ticket mapFrom(Customer customer, Route route) {
        return Ticket.builder()
                .price(route.getPrice())
                .route(route)
                .customer(customer)
                .build();
    }

    public TicketResponse mapFrom(Ticket ticket) {
        return new TicketResponse()
                .id(ticket.getId())
                .routeId(ticket.getRoute().getId())
                .price(ticket.getPrice().toString())
                .startLocation(ticket.getRoute().getStartLocation())
                .endLocation(ticket.getRoute().getEndLocation());


    }

}
