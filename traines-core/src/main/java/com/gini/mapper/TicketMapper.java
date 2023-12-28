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

    public Ticket mapFrom(TicketRequest ticketRequest, Route route) {
        var customerRequest = ticketRequest.getCustomer();
        var customer = Customer.builder()
                .id(customerRequest.getId())
                .email(customerRequest.getEmail())
                .build();

        return Ticket.builder()
                .price(route.getPrice())
                .routes(Set.of(route))
                .customer(customer)
                .build();

    }

    public Ticket mapFrom(TicketRequest ticketRequest, Customer customer, Route route) {
        return Ticket.builder()
                .price(route.getPrice())
                .routes(Set.of(route))
                .customer(customer)
                .build();
    }


}
