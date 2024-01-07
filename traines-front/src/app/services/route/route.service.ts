import {inject, Injectable} from "@angular/core";
import {RouteGatewayService} from "../gateway/route.gateway.service";
import {CreateRoute} from "../../dto/request/create.route";
import {Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class RouteService {


    private routeGateway: RouteGatewayService = inject(RouteGatewayService);

    createRoute(createRoute: CreateRoute): Observable<void> {
        return this.routeGateway.createRoute(createRoute);
    }

}
