package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import com.gini.mapper.TicketMapper;
import com.gini.util.GenerateCustomer;
import gini.trainsapi.model.TicketResponsePaginated;
import gini.trainscore.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TrainsCoreGateway trainsCoreGateway;
    private final TicketMapper ticketMapper;

    public void createTicket(String routeId) {
        var customer = GenerateCustomer.generateCustomer();
        var ticket = new TicketRequest(routeId, customer);
        log.debug("sending create ticket request to gateway");
        trainsCoreGateway.createTicket(ticket);
    }


    public TicketResponsePaginated getUsersTicketsPaginated(Integer pageNumber, String customerId) {
        log.debug("sending page number: {} for customerId: {} to gateway", pageNumber, customerId);
        var ticketsPaginated = trainsCoreGateway.getUsersTicketsPaginated(pageNumber, customerId);
        log.info("generate response for customerId: {}", customerId);
        return ticketMapper.mapFrom(ticketsPaginated);
    }

    public TicketResponsePaginated getAllTicketsPaginated(Integer pageNumber) {
        log.debug("sending page number: {} to gateway", pageNumber);
        var ticketsPaginated = trainsCoreGateway.getAllTicketsPaginated(pageNumber);
        log.info("generate response for page number: {}", pageNumber);
        return ticketMapper.mapFrom(ticketsPaginated);
    }

    public TicketResponsePaginated getTicketsByDestination(Integer pageNumber, String destination) {
        log.debug("sending page number: {} and destination: {} to gateway", pageNumber, destination);
        var ticketsPaginated = trainsCoreGateway.getTicketsByDestination(pageNumber, destination);
        log.info("generate response for page number: {} and destination: {}", pageNumber, destination);
        return ticketMapper.mapFrom(ticketsPaginated);
    }




}
