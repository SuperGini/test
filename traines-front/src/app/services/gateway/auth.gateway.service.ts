import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Token} from "../../util/token";
import {httpHeaders, httpParams} from "../../util/token.parameters";

@Injectable({providedIn: 'root'})
export class AuthGatewayService {

    private httpClient: HttpClient = inject(HttpClient);

    getAuthToken2(code: string): Observable<Token> {
        return this.httpClient.post<Token>('http://localhost:8080/oauth2/token',
            null,
            {
                headers: httpHeaders(),
                params: httpParams(code)
            });
    }


}
