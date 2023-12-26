package com.gini.mapper;

import com.gini.model.Customer;
import com.gini.model.Route;
import com.gini.model.Ticket;
import gin.model.TicketRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class TicketMapper {

    public Ticket mapFrom(TicketRequest ticketRequest) {
        var routeRequest = ticketRequest.getRoute();
        var customerRequest = ticketRequest.getCustomer();

        var route = Route.builder()
                .id(routeRequest.getId())
                .startLocation(routeRequest.getStartLocation())
                .endLocation(routeRequest.getEndLocation())
                .build();

        var customer = Customer.builder()
                .id(customerRequest.getId())
                .email(customerRequest.getEmail())
                .build();

        return Ticket.builder()
                .price(new BigDecimal(ticketRequest.getPrice()))
                .routes(Set.of(route))
                .customer(customer)
                .build();

    }

    public Ticket mapFrom(TicketRequest ticketRequest, Customer customer, Route route) {
        return Ticket.builder()
                .price(new BigDecimal(ticketRequest.getPrice()))
                .routes(Set.of(route))
                .customer(customer)
                .build();
    }


}
