import {JwtHelperService} from "@auth0/angular-jwt";
import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";

import {map} from "rxjs";
import {LocalStorageService} from "../services/local.storage.service";
import {AuthService} from "../services/auth/auth.service";


export const canActivateRoute: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {

    const localStorageService = inject(LocalStorageService);

    const jwtHelper: JwtHelperService = new JwtHelperService();
    const accessToken: string = <string>localStorageService.getItem('id_token');
    const isTokenExpired: boolean = jwtHelper.isTokenExpired(accessToken);

    console.log(`is token expired?: ${isTokenExpired}`);

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

    return true;
};


