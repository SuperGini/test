import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Token} from "../../util/token";
import {httpHeaders, httpParams} from "../../util/token.parameters";
import {LocalStorageService} from "../local.storage.service";

@Injectable({providedIn: 'root'})
export class AuthGatewayService {

    private httpClient: HttpClient = inject(HttpClient);
    private localStorageService: LocalStorageService = inject(LocalStorageService);

    getAuthToken2(code: string): Observable<Token> {
        return this.httpClient.post<Token>('http://localhost:8080/oauth2/token',
            null,
            {
                headers: httpHeaders(),
                params: httpParams(code)
            });
    }

    getAuthTokenForRefreshToken(){
        const refreshToken = this.localStorageService.getItem("refresh_token");

        // const httpHeaders = new HttpHeaders()
        //     .set("content-type", "application/json");

        const queryParams = new HttpParams()
            .set("grant_type", "refresh_token")
            .set("refresh_token", refreshToken);

        return this.httpClient.post<Token>(`http://localhost:8080/oauth2/token`, null, {headers: httpHeaders(), params: queryParams});


    }


}
