import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";
import {inject, PLATFORM_ID} from "@angular/core";
import {isPlatformBrowser} from "@angular/common";
import {LocalStorageService} from "../services/local.storage.service";


const jwtHelper: JwtHelperService = new JwtHelperService();

export const canActivateRouteGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {

    const platformId: Object = inject(PLATFORM_ID);
    const localStorageService = inject(LocalStorageService);

    if (isPlatformBrowser(platformId)) {
        const accessToken: string = localStorageService.getItem('id_token');
        const y = jwtHelper.decodeToken(accessToken);
        const authorities = y.authorities as string [];

        if (authorities.includes("ADMIN")) {
            return true;
        }
    }
    return false;
};
