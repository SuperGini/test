import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TicketResponsePaginated} from "../../dto/response/ticket.response.paginated";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({providedIn: "root"})
export class TicketGatewayService {

    private httpClient = inject(HttpClient);

    public getAllTicketsPaginated(pageNumber: number): Observable<TicketResponsePaginated> {
        return this.httpClient.get<TicketResponsePaginated>(`${environment.trainsApiUrl}/tickets/${pageNumber}`);
    }

    public getUserTicketsPaginated(pageNumber: number, userId: string): Observable<TicketResponsePaginated> {
        const queryParams: HttpParams = new HttpParams()
            .set("pageNumber", pageNumber)
            .set("customerId", userId);
        return this.httpClient.get<TicketResponsePaginated>(`${environment.trainsApiUrl}/tickets`, {params: queryParams});
    }

    public getTicketsByDestination(pageNumber: number, destination: string): Observable<TicketResponsePaginated> {
        return this.httpClient.get<TicketResponsePaginated>(`${environment.trainsApiUrl}/${pageNumber}/${destination}`);
    }

    public buyTicket(routId: string): Observable<void> {
        return this.httpClient.post<void>(`${environment.trainsApiUrl}/ticket/${routId}`, null);
    }


}
