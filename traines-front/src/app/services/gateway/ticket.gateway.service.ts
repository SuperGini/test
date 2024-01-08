import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TicketResponsePaginated} from "../../dto/response/ticket.response.paginated";
import {Observable} from "rxjs";
import {CreateTicket} from "../../dto/request/create.ticket";

@Injectable({providedIn: "root"})
export class TicketGatewayService {

    private httpClient = inject(HttpClient);

    public getAllTicketsPaginated(pageNumber: number): Observable<TicketResponsePaginated> {
        return this.httpClient.get<TicketResponsePaginated>(`http://localhost:9010/tickets/${pageNumber}`);
    }

    public getUserTicketsPaginated(pageNumber: number, userId: string): Observable<TicketResponsePaginated> {
        const queryParams: HttpParams = new HttpParams()
            .set("pageNumber", pageNumber)
            .set("customerId", userId);
        return this.httpClient.get<TicketResponsePaginated>(`http://localhost:9010/tickets`, {params: queryParams});
    }

    public getTicketsByDestination(pageNumber: number, destination: string): Observable<TicketResponsePaginated> {
        return this.httpClient.get<TicketResponsePaginated>(`http://localhost:9010/tickets/${pageNumber}/${destination}`);
    }

    public buyTicket(routId: string): Observable<void> {
        return this.httpClient.post<void>(`http://localhost:9010/ticket/${routId}`, null);
    }



}
