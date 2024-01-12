import {JwtHelperService} from "@auth0/angular-jwt";
import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject, PLATFORM_ID} from "@angular/core";

import {map} from "rxjs";
import {LocalStorageService} from "../services/local.storage.service";
import {AuthService} from "../services/auth/auth.service";
import {isPlatformBrowser} from "@angular/common";

const jwtHelper: JwtHelperService = new JwtHelperService();

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {

    const platformId = inject(PLATFORM_ID);
    const localStorageService = inject(LocalStorageService);
    const router = inject(Router);

    if(isPlatformBrowser(platformId)) { // -> so it will only be called on browser side
        const accessToken: string = localStorageService.getItem('id_token');
        const isTokenExpired: boolean = jwtHelper.isTokenExpired(accessToken);

        console.log(`is token expired?: ${isTokenExpired}`);

        if(!localStorageService.getItem('refresh_token')) {
            router.navigate(['/main'])
            return false;
        }

        if (isTokenExpired) {
            return inject(AuthService).getAuthTokenForRefreshToken()
                                      .pipe(
                                          map(token => {
                                              localStorageService.setItem('id_token', token.id_token);
                                              localStorageService.setItem('refresh_token', token.refresh_token);


                                              console.log(`id_token = ${token.id_token}`);
                                              console.log(`refresh_token = ${token.refresh_token}`);


                                              return true;
                                          }),
                                      );
        }
    }

    return true;
};


