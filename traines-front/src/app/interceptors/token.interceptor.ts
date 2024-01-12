import {HttpErrorResponse, HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {LocalStorageService} from "../services/local.storage.service";
import {catchError, throwError} from "rxjs";
import {AuthService} from "../services/auth/auth.service";

export const tokenInterceptor: HttpInterceptorFn = (request, next) => {

    const localServiceStorage = inject(LocalStorageService);

    console.log("------------------------------------------------------------");

    if (!request.url.includes("oauth2/token")) {
        const httpHeaders = request.headers
                                   .append("content-type", "application/json")
                                   .append('Authorization', `Bearer ${localServiceStorage.getItem("id_token")}`);

        const modifiedRequest = request.clone({headers: httpHeaders});

        //add the headers to request and send the request forward
        return next(modifiedRequest);  //need this to send the request forward. Otherwise, the request wil not be forwarded

    }
    return next(request);
};


/**
 * *****How To Interceptors in Angular 17
 * https://www.youtube.com/watch?v=hy_8JsOIVug&ab_channel=AyyazTech
 *
 * https://stackoverflow.com/questions/77624853/interceptor-not-intercepting-in-angular-17
 * */
