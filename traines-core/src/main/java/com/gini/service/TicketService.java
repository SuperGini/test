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

import java.util.List;

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
        var routeId = ticketRequest.getRoute().getId();

        var customer = customerRepository.findById(customerId);
        var route = routeRepository.getReferenceById(routeId);

        if(customer.isPresent()){
            log.info("mapping ticket request");
            var ticket = ticketMapper.mapFrom(ticketRequest, customer.get(), route);
            ticketRepository.save(ticket); //creates new ticket and sets the customer and route on ticket
            return;
        }
        log.info("mapping ticket request");
        var ticket = ticketMapper.mapFrom(ticketRequest);
        ticketRepository.save(ticket); //creates new customer and ticket and sets the route
    }

}
