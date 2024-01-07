import {Component, inject} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {Router, RouterOutlet} from "@angular/router";

@Component({
    selector: "central-component",
    templateUrl: "central.html",
    styleUrl: "central.css",
    imports: [
        MatIconModule,
        RouterOutlet
    ],
    standalone: true
})

export class CentralComponent {

    private router: Router = inject(Router);

    toHomePage() {
        this.router.navigate(['central/home']);
    }

    toTicketsPage() {
        this.router.navigate(['central/tickets']);
    }

    toRoutePage() {
        this.router.navigate(['central/route']);
    }

}
