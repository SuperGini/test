import {inject, Injectable} from "@angular/core";
import {RouteGatewayService} from "../gateway/route.gateway.service";
import {CreateRoute} from "../../dto/request/create.route";
import {Observable} from "rxjs";
import {RouteResponsePaginated} from "../../dto/response/route.response.paginated";

@Injectable({providedIn: "root"})
export class RouteService {


    private routeGateway: RouteGatewayService = inject(RouteGatewayService);

    createRoute(createRoute: CreateRoute): Observable<void> {
        return this.routeGateway.createRoute(createRoute);
    }

    getAllRoutesPaginated(pageNumber: number): Observable<RouteResponsePaginated> {
        return this.routeGateway.getAllRoutesPaginated(pageNumber);
    }

    getAllRoutesByDestination(pageNumber: number, destination: string): Observable<RouteResponsePaginated> {
        return this.routeGateway.getAllRoutesByDestination(pageNumber, destination);
    }

}
