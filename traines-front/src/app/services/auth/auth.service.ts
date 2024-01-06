import {inject, Injectable, PLATFORM_ID} from "@angular/core";
import {GatewayService} from "../../gateway/gateway.service";
import {Router} from "@angular/router";
import {LocalStorageService} from "../local.storage.service";
import {take} from "rxjs";
import crypto from "crypto";
import {AppComponent} from "../../app.component";
import {isPlatformBrowser} from "@angular/common";

@Injectable({providedIn: 'root'})
export class AuthService {


    public code: string = '';

    private gatewayService: GatewayService = inject(GatewayService);
    private router: Router = inject(Router);
    private localStorageService = inject(LocalStorageService);
    private serverPlatform = inject(PLATFORM_ID);


    getAuthToken() {
        return this.gatewayService.getAuthToken2(this.code)
                   .pipe(take(1))
                   .subscribe(token => {

                       this.localStorageService.setItem('id_token', token.id_token);
                       this.localStorageService.setItem('refresh_token', token.refresh_token);

                       //    console.log(`vvvvvv ${crypto.createHash('sha256').update('message').digest('hex')}`);

                       this.router.navigate(['/central/home']);



                       console.log(`id_token = ${token.id_token}`);
                       console.log(`refresh_token = ${token.refresh_token}`);
                   });
    }


}
