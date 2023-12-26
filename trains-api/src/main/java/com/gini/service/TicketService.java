package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import gin.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TrainsCoreGateway trainsCoreGateway;


    public void createTicket(TicketRequest ticketRequest) {
        trainsCoreGateway.createTicket(ticketRequest);
    }
}
