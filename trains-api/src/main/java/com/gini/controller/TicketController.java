package com.gini.controller;

import com.gini.service.TicketService;
import gini.trainsapi.api.TicketApi;
import gini.trainsapi.model.TicketResponsePaginated;
import gini.trainscore.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {

    private final TicketService ticketService;

    @Override
    public ResponseEntity<Void> createTicket(String routeId) {
        log.info("create ticket for routId: {}", routeId);
        ticketService.createTicket(routeId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TicketResponsePaginated> getUsersTicketsPaginated(Integer pageNumber, String customerId) {
        log.info("sending request for customerId: {} for page: {}", customerId, pageNumber);
        var tickets =  ticketService.getUsersTicketsPaginated(pageNumber, customerId);
        return ResponseEntity.ok(tickets);
    }

    @Override
    public ResponseEntity<TicketResponsePaginated> getAllTicketsPaginated(Integer pageNumber) {
        log.info("sending request for page: {}", pageNumber);
        var tickets =  ticketService.getAllTicketsPaginated(pageNumber);
        return ResponseEntity.ok(tickets);
    }

}
