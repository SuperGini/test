import {Routes} from '@angular/router';
import {MainComponent} from "./components/main/main.component";
import {HomeComponent} from "./components/central/home/home.component";
import {TicketsComponent} from "./components/central/tickets/tickets.component";
import {RouteComponent} from "./components/central/route/route.component";
import {CentralComponent} from "./components/central/central.component";
import {PageNotFoundComponent} from "./components/notfound/page.not.found.component";
import {AuthComponent} from "./components/auth/auth.component";
import {canActivateRoute} from "./guard/auht.guard";

export const routes: Routes = [
    {
        path: 'main',
        component: MainComponent,
        pathMatch: 'full'
    },
    {
        path: "central",
        component: CentralComponent,
        children: [
            {
                path: "home",
                component: HomeComponent,
                pathMatch: "full",
                canActivate: [canActivateRoute]
            },
            {
                path: "tickets",
                component: TicketsComponent,
                pathMatch: "full",
                canActivate: [canActivateRoute]
            },
            {
                path: "route",
                component: RouteComponent,
                pathMatch: "full",
                // canActivate: [canActivateRoute]
            }
        ]
    },
    {
        path: "authorized",
        redirectTo: 'auth',
        pathMatch: 'full'
    },

    {
        path: "auth",
        component: AuthComponent,
        pathMatch: "full"
    },
    {
        path: '',
        redirectTo: 'main',
        pathMatch: 'full'
    },

    {
        path: '*',
        redirectTo: 'auth',
        pathMatch: 'full'
    },

    {
        path: "**",
        component: PageNotFoundComponent,
    }

];
