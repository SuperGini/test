package com.gini.controller;

import com.gini.service.TicketService;
import gini.trainsapi.api.TicketApi;
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
        ticketService.createTicket(routeId);
        return ResponseEntity.ok().build();
    }
}
