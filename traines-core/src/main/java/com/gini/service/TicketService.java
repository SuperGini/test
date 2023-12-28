package com.gini.service;

import com.gini.mapper.TicketMapper;
import com.gini.repository.CustomerRepository;
import com.gini.repository.RouteRepository;
import com.gini.repository.TicketRepository;
import gin.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final RouteRepository routeRepository;

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void createTicket(TicketRequest ticketRequest) {

        var customerId = ticketRequest.getCustomer().getId();
        var routeId = ticketRequest.getRouteId();

        var customerOpt = customerRepository.findById(customerId);
        var route = routeRepository.getReferenceById(routeId);

        log.info("mapping ticket request");
        var ticket = customerOpt
                .map(customer -> ticketMapper.mapFrom(ticketRequest, customer, route)) //creates new ticket and sets the customer and route on ticket
                .orElseGet(() -> ticketMapper.mapFrom(ticketRequest, route)); //creates new customer and ticket and sets the route

        ticketRepository.save(ticket);

    }

}
