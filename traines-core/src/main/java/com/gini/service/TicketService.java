package com.gini.service;

import com.gini.mapper.TicketMapper;
import com.gini.repository.CustomerRepository;
import com.gini.repository.RouteRepository;
import com.gini.repository.TicketRepository;
import gin.model.TicketRequest;
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
                .map(customer -> ticketMapper.mapFrom(customer, route)) //creates new ticket and sets the customer and route on ticket
                .orElseGet(() -> ticketMapper.mapFrom(ticketRequest, route)); //creates new customer and ticket and sets the route
        log.info("saving ticket for customerId: {}", ticketRequest.getCustomer().getId());
        ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public TicketResponsePaginated getUserTicketsPaginated(Integer pageNumber, String customerId) {
        var nrOfElementsOnPage = 5;
        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);

        var ticketsPage = ticketRepository.getUserTicketPaginated(page, customerId);

        Long totalTickets = ticketsPage.getTotalElements();

        log.info("mapping ticket response");
        var ticketResponses = ticketsPage.stream()
                .map(ticketMapper::mapFrom)
                .toList();
        return new gin.model.TicketResponsePaginated()
                .ticketResponses(ticketResponses)
                .totalTickets(totalTickets);
    }

    @Transactional(readOnly = true)
    public TicketResponsePaginated getTicketsPaginated(Integer pageNumber) {
        var nrOfElementsOnPage = 5;
        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);

        var ticketsPage = ticketRepository.getTicketPaginated(page);

        Long totalTickets = ticketsPage.getTotalElements();

        log.info("mapping ticket response");
        var ticketResponses = ticketsPage.stream()
                .map(ticketMapper::mapFrom)
                .toList();
        return new TicketResponsePaginated()
                .ticketResponses(ticketResponses)
                .totalTickets(totalTickets);
    }

}
