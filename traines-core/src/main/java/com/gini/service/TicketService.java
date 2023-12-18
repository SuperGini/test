package com.gini.service;

import com.gini.mapper.TicketMapper;
import com.gini.repository.TicketRepository;
import gin.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    @Transactional
    public void createTicket(TicketRequest ticketRequest) {
        var ticket = ticketMapper.mapFrom(ticketRequest);
        ticketRepository.save(ticket);
    }

}
