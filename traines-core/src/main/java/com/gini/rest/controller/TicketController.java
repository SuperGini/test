package com.gini.rest.controller;

import com.gini.service.TicketService;
import gin.model.TicketRequest;
import gini.api.TicketApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {

    private final TicketService ticketService;


    @Override
    public ResponseEntity<Void> createTicket(TicketRequest ticketRequest) {
        ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok().build();
    }
}
