import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CreateRoute} from "../../dto/request/create.route";
import {Observable} from "rxjs";
import {LocalStorageService} from "../local.storage.service";

@Injectable({providedIn: "root"})
export class RouteGatewayService {

    private httClient = inject(HttpClient);
    private localStorageService = inject(LocalStorageService);

    createRoute(createRoute: CreateRoute): Observable<void>{

        //todo: need to move this shit into a interceptor!!!!
        const httpHeaders = new HttpHeaders()
            .set("content-type", "application/json")
            .set('Authorization', `Bearer ${this.localStorageService.getItem("id_token")}`)

       return  this.httClient.post<void>("http://localhost:9010/route", createRoute, {headers: httpHeaders});
    }

}
