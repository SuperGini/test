package com.gini.rest.dto.request;

public record TicketRequest(
        String price,
        RouteRequest route,
        CustomerRequest customer
) {
}
