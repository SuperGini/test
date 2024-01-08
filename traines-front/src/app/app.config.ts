import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideClientHydration, withHttpTransferCacheOptions} from '@angular/platform-browser';
import {provideHttpClient, withFetch, withInterceptors} from "@angular/common/http";
import {provideAnimations} from '@angular/platform-browser/animations';
import {tokenInterceptor} from "./interceptors/token.interceptor";

export const appConfig: ApplicationConfig = {
    providers: [
        provideRouter(routes),
        provideClientHydration(withHttpTransferCacheOptions({includePostRequests: true})), // so it doesn't do 2 calls for post
        provideHttpClient(
            withFetch(), // -> needed for server side rendering (HTTP CLIENT)
            withInterceptors(   // -> adds interceptors to http calls
                [
                    tokenInterceptor
                ])),
        provideAnimations(),
    ]
};
