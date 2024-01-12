import {Component, inject, OnInit, PLATFORM_ID, signal} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {Router, RouterOutlet} from "@angular/router";
import {MatPaginatorModule} from "@angular/material/paginator";
import {isPlatformBrowser, NgClass} from "@angular/common";
import {isHomeActive, isRouteActive, isTicketsActive} from "../../state/state";
import {JwtHelperService} from "@auth0/angular-jwt";
import {LocalStorageService} from "../../services/local.storage.service";

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

export class CentralComponent implements OnInit{

    private jwtHelper: JwtHelperService = new JwtHelperService();
    private platformId: Object = inject(PLATFORM_ID);
    private localStorageService = inject(LocalStorageService);

    isAdmin = signal<boolean>(false);

    ngOnInit(): void {
        if(isPlatformBrowser(this.platformId)){
            const accessToken: string = this.localStorageService.getItem('id_token');
            const y = this.jwtHelper.decodeToken(accessToken);
            const authorities = y.authorities as string [];
            this.isAdmin.set(authorities.includes("ADMIN"))
        }
    }

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
