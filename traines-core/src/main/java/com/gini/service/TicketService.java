package com.gini.service;

import com.gini.mapper.TicketMapper;
import com.gini.repository.TicketRepository;
import gin.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    @Transactional
    public void createTicket(TicketRequest ticketRequest) {
        log.info("mapping ticket request");
        var ticket = ticketMapper.mapFrom(ticketRequest);
      //  log.info("saving ticket: {} to database", ticket);
        ticketRepository.save(ticket);
    }

}
