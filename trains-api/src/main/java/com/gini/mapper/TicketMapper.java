package com.gini.mapper;

import gini.trainsapi.model.TicketResponse;
import gini.trainsapi.model.TicketResponsePaginated;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponsePaginated mapFrom (gini.trainscore.model.TicketResponsePaginated tickets) {
        var ticketsResponse = tickets.getTicketResponses().stream()
                .map(this::mapFrom)
                .toList();

       return new TicketResponsePaginated()
               .totalTickets(tickets.getTotalTickets())
               .ticketResponses(ticketsResponse);

    }

    private TicketResponse mapFrom(gini.trainscore.model.TicketResponse ticket) {
        return new TicketResponse()
                .id(ticket.getId())
                .routeId(ticket.getRouteId())
                .price(ticket.getPrice())
                .startLocation(ticket.getStartLocation())
                .endLocation(ticket.getEndLocation());
    }





}
