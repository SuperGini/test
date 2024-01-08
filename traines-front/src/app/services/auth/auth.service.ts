import {inject, Injectable, PLATFORM_ID} from "@angular/core";
import {AuthGatewayService} from "../gateway/auth.gateway.service";
import {Router} from "@angular/router";
import {LocalStorageService} from "../local.storage.service";
import {take} from "rxjs";

@Injectable({providedIn: 'root'})
export class AuthService {


    public code: string = '';

    private gatewayService: AuthGatewayService = inject(AuthGatewayService);
    private router: Router = inject(Router);
    private localStorageService = inject(LocalStorageService);


    getAuthToken() {
        return this.gatewayService.getAuthToken2(this.code)
                   .pipe(take(1))
                   .subscribe(token => {

                       this.localStorageService.setItem('id_token', token.id_token);
                       this.localStorageService.setItem('refresh_token', token.refresh_token);

                       this.router.navigate(['/central/home']);

                       console.log(`id_token = ${token.id_token}`);
                       console.log(`refresh_token = ${token.refresh_token}`);
                   });
    }

    getAuthTokenForRefreshToken() {
        return this.gatewayService.getAuthTokenForRefreshToken();
            // .pipe(take(1))
            // .subscribe(token => {
            //     this.localStorageService.setItem('id_token', token.id_token);
            //     this.localStorageService.setItem('refresh_token', token.refresh_token);
            //
            //     console.log(`id_token via refresh token = ${token.id_token}`);
            //     console.log(`refresh_token via refresh token = ${token.refresh_token}`);
            // })
    }




}
