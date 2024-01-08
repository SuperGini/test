import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {CreateRoute} from "../../dto/request/create.route";
import {Observable} from "rxjs";
import {RouteResponsePaginated} from "../../dto/response/route.response.paginated";

@Injectable({providedIn: "root"})
export class RouteGatewayService {

    private httClient = inject(HttpClient);

    createRoute(createRoute: CreateRoute): Observable<void> {
        return this.httClient.post<void>("http://localhost:9010/route", createRoute);
    }

    getAllRoutesPaginated(pageNumber: number): Observable<RouteResponsePaginated> {
        const queryParam = new HttpParams()
            .set('pageNumber', pageNumber);
        return this.httClient.get<RouteResponsePaginated>(`http://localhost:9010/route`, {params: queryParam});
    }

    getAllRoutesByDestination(pageNumber: number, destination: string): Observable<RouteResponsePaginated>{
        return this.httClient.get<RouteResponsePaginated>(`http://localhost:9010/route/${pageNumber}/${destination}`);
    }

}
