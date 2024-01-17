import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {CreateRoute} from "../../dto/request/create.route";
import {Observable} from "rxjs";
import {RouteResponsePaginated} from "../../dto/response/route.response.paginated";
import {environment} from "../../../environments/environment";

@Injectable({providedIn: "root"})
export class RouteGatewayService {

    private httClient = inject(HttpClient);

    createRoute(createRoute: CreateRoute): Observable<void> {
        return this.httClient.post<void>(`${environment.trainsApiUrl}/route`, createRoute);
    }

    getAllRoutesPaginated(pageNumber: number): Observable<RouteResponsePaginated> {
        const queryParam = new HttpParams()
            .set('pageNumber', pageNumber);
        return this.httClient.get<RouteResponsePaginated>(`${environment.trainsApiUrl}/route`, {params: queryParam});
    }

    getAllRoutesByDestination(pageNumber: number, destination: string): Observable<RouteResponsePaginated>{
        return this.httClient.get<RouteResponsePaginated>(`${environment.trainsApiUrl}/route/${pageNumber}/${destination}`);
    }

}
