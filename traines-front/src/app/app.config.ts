import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideClientHydration, withHttpTransferCacheOptions} from '@angular/platform-browser';
import {HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";

export const appConfig: ApplicationConfig = {
    providers: [
        provideRouter(routes),
        provideClientHydration(withHttpTransferCacheOptions({includePostRequests: true})), //cash the post so angular does not do 2 calls when getting the token, and get error because we use auth_code 2 times
        provideHttpClient(withFetch()) // -> needed for serv side rendering (HTTP CLIENT)

    ]
};
