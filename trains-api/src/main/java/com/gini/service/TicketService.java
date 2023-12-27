package com.gini.service;

import com.gini.gateway.TrainsCoreGateway;
import com.gini.util.GenerateCustomer;
import gini.trainscore.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TrainsCoreGateway trainsCoreGateway;

    public void createTicket(String routeId) {
        var customer = GenerateCustomer.generateCustomer();
        var ticket = new TicketRequest(routeId, customer);
        trainsCoreGateway.createTicket(ticket);
    }
}
