import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {TicketResponsePaginated} from "../../dto/response/ticket.response.paginated";
import {Observable} from "rxjs";
import {LocalStorageService} from "../local.storage.service";

@Injectable({providedIn:"root"})
export class TicketGatewayService {

    private httpClient = inject(HttpClient);
    private localStorageService = inject(LocalStorageService);

    public getAllTicketsPaginated(pageNumber: number): Observable<TicketResponsePaginated> {
        const httpHeaders = new HttpHeaders()
            .set("content-type", "application/json")
            .set('Authorization', `Bearer ${this.localStorageService.getItem("id_token")}`)
        return this.httpClient.get<TicketResponsePaginated>(`http://localhost:9010/tickets/${pageNumber}`, {headers: httpHeaders});
    }

    public getUserTicketsPaginated(pageNumber: number, userId: string): Observable<TicketResponsePaginated> {
        const httpHeaders = new HttpHeaders()
            .set("content-type", "application/json")
            .set('Authorization', `Bearer ${this.localStorageService.getItem("id_token")}`)
        const queryParams = new HttpParams()
            .set("pageNumber", pageNumber)
            .set("customerId", userId);

        return this.httpClient.get<TicketResponsePaginated>(`http://localhost:9010/tickets`, {headers: httpHeaders, params: queryParams});
    }




}
