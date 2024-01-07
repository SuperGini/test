import {Component, inject, OnInit} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {Router, RouterOutlet} from "@angular/router";
import {MatPaginatorModule} from "@angular/material/paginator";
import {NgClass} from "@angular/common";
import {isHomeActive, isRouteActive, isTicketsActive} from "../../state/state";

@Component({
    selector: "central-component",
    templateUrl: "central.html",
    styleUrl: "central.css",
    imports: [
        MatIconModule,
        RouterOutlet,
        MatPaginatorModule,
        NgClass
    ],
    standalone: true
})

export class CentralComponent{

    private router: Router = inject(Router);
    protected readonly isHomeActive = isHomeActive;

    toHomePage() {
        this.router.navigate(['central/home']);
    }

    toTicketsPage() {
        this.router.navigate(['central/tickets']);
    }

    toRoutePage() {
        this.router.navigate(['central/route']);
    }


    protected readonly isTicketsActive = isTicketsActive;
    protected readonly isRouteActive = isRouteActive;
}
