import {Routes} from '@angular/router';
import {MainComponent} from "./components/main/main.component";
import {HomeComponent} from "./components/central/home/home.component";
import {TicketsComponent} from "./components/central/tickets/tickets.component";
import {RouteComponent} from "./components/central/route/route.component";
import {CentralComponent} from "./components/central/central.component";
import {PageNotFoundComponent} from "./components/notfound/page.not.found.component";

export const routes: Routes = [
    {
        path: 'main',
        component: MainComponent,
        pathMatch: 'full'
    },
    {
        path: "central",
        component: CentralComponent,
        // pathMatch: "full",
        children: [
            {
                path: "home",
                component: HomeComponent,
                // pathMatch: "full"
            },
            {
                path: "tickets",
                component: TicketsComponent,
                pathMatch: "full"
            },
            {
                path: "route",
                component: RouteComponent,
                pathMatch: "full"
            }
        ]
    },
    {
        path: "home",
        component: HomeComponent,
        pathMatch: "full"
    },

    {
        path: "**",
        component: PageNotFoundComponent,

    }





];
