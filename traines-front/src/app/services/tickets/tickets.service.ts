import {inject, Injectable} from "@angular/core";
import {TicketGatewayService} from "../gateway/ticket.gateway.service";
import {Observable} from "rxjs";
import {TicketResponsePaginated} from "../../dto/response/ticket.response.paginated";
import {CreateTicket} from "../../dto/request/create.ticket";

@Injectable({providedIn: 'root'})
export class TicketsService {

    private ticketGateway: TicketGatewayService = inject(TicketGatewayService);


    public getAllTicketsPaginated(pageNumber: number): Observable<TicketResponsePaginated> {
        return this.ticketGateway.getAllTicketsPaginated(pageNumber);
    }

    public getUserTicketsPaginated(pageNumber: number, userId: string): Observable<TicketResponsePaginated> {
        return this.ticketGateway.getUserTicketsPaginated(pageNumber, userId);
    }

    public getTicketsByDestination(pageNumber: number, userId: string): Observable<TicketResponsePaginated> {
        return this.ticketGateway.getTicketsByDestination(pageNumber, userId);
    }

    public buyTicket(routeId: string): Observable<void> {
        return this.ticketGateway.buyTicket(routeId);
    }

}
