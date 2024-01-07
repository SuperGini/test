import {Component, Inject, OnDestroy, OnInit, PLATFORM_ID, signal} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {TicketsService} from "../../../services/tickets/tickets.service";
import {TicketResponsePaginated} from "../../../dto/response/ticket.response.paginated";
import {isPlatformBrowser, NgForOf} from "@angular/common";
import {MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {JwtHelperService} from "@auth0/angular-jwt";
import {LocalStorageService} from "../../../services/local.storage.service";
import {Router} from "@angular/router";
import {isHomeActive} from "../../../state/state";

@Component({
    selector: "home-component",
    templateUrl: "home.html",
    styleUrl: "home.css",
    imports: [
        MatIconModule,
        NgForOf,
        MatPaginatorModule
    ],
    standalone: true

})
export class HomeComponent implements OnInit, OnDestroy {

    length = 0;
    pageSize = 0;
    pageIndex = 0;
    pageSizeOptions = [5, 10];

    hidePageSize = true;
    showPageSizeOptions = true;
    showFirstLastButtons = true;
    disabled = false;

    userId: string = '';

    isBrowser: boolean;

    public ticket = signal<TicketResponsePaginated>(null);

    constructor(private ticketService: TicketsService,
                @Inject(PLATFORM_ID) platformId: Object,
                private localStorageService: LocalStorageService
    ) {

        this.isBrowser = isPlatformBrowser(platformId);
        if (this.isBrowser) {
            const jwtHelper: JwtHelperService = new JwtHelperService();
            const token: string = <string>this.localStorageService.getItem("id_token");
            const y = jwtHelper.decodeToken(token);
            this.userId = y.userId; //GET userId form JWT

            this.ticketService.getUserTicketsPaginated(0, this.userId)
                .subscribe(x => {
                    this.ticket.set(x);
                    this.length = x.totalTickets;
                    console.log(x.ticketResponses);
                    console.log(x.totalTickets);
                });
        }
    }


    ngOnInit(): void {
        isHomeActive.set(true);

    }

    ngOnDestroy(): void {
        isHomeActive.set(false);
    }

    handlePageEvent(e: PageEvent) {
        this.ticketService.getUserTicketsPaginated(e.pageIndex, this.userId)
            .subscribe(x => {
                this.length = x.totalTickets;
                this.ticket.set(x);

            });
    }

}
