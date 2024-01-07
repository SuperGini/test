package com.gini.rest.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketResponse {
    private String price;
    private String startLocation;
    private String endLocation;
}
