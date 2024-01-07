package com.gini.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketResponsePaginated {

    private List<TicketResponse> ticketResponses;
    private Long totalTickets;

}
