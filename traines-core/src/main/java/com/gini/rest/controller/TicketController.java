package com.gini.rest.controller;

import com.gini.rest.dto.response.TicketResponsePaginated;
import com.gini.service.TicketService;
import gin.model.TicketRequest;
import gini.api.TicketApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {

    private final TicketService ticketService;

    @Override
    public ResponseEntity<Void> createTicket(TicketRequest ticketRequest) {
        log.debug("ticket request received");
        ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/tickets")
    public ResponseEntity<TicketResponsePaginated> getUsersTicketsPaginated(@RequestParam Integer pageNumber, @RequestParam String customerId){

        var ticketsPaginated = ticketService.getUserTicketsPaginated(pageNumber, customerId);

        return ResponseEntity.ok(ticketsPaginated);

    }


}
