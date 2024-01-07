import {TicketResponse} from "./ticket.response";

export interface TicketResponsePaginated {
    totalTickets: number;
    ticketResponses: TicketResponse []

}
