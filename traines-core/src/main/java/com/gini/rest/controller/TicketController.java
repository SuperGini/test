package com.gini.rest.controller;

import com.gini.rest.dto.request.TicketRequest;
import com.gini.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket")
    public void createTicket(@RequestBody TicketRequest ticketRequest){
        ticketService.createTicket(ticketRequest);
    }


}
