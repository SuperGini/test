import {Component, Inject, OnDestroy, OnInit, PLATFORM_ID, signal} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {TicketsService} from "../../../services/tickets/tickets.service";
import {isPlatformBrowser} from "@angular/common";
import {isTicketsActive} from "../../../state/state";
import {FormsModule} from "@angular/forms";
import {RouteService} from "../../../services/route/route.service";
import {RouteResponsePaginated} from "../../../dto/response/route.response.paginated";

@Component({
    selector: "tickets-component",
    templateUrl: "tickets.html",
    styleUrl: "tickets.css",
    imports: [
        MatIconModule,
        MatPaginatorModule,
        FormsModule
    ],
    standalone: true
})

export class TicketsComponent implements OnInit, OnDestroy {
    length = 0;
    pageSize = 0;
    pageIndex = 0;
    pageSizeOptions = [5, 10];

    hidePageSize = true;
    showPageSizeOptions = true;
    showFirstLastButtons = true;
    disabled = false;

    public ticket = signal<RouteResponsePaginated>(null);

    isBrowser: boolean;

    destination: string = '';

    constructor(private ticketService: TicketsService,
                @Inject(PLATFORM_ID) platformId: Object,
                private routeService: RouteService,
    ) {
        this.isBrowser = isPlatformBrowser(platformId);
        if (this.isBrowser) {

            this.routeService.getAllRoutesPaginated(0)
                .subscribe(x => {
                    this.ticket.set(x);
                    this.length = x.totalRoutes;
                    console.log(x.totalRoutes);
                    console.log(x.routeResponses);
                });
        }
    }

    ngOnInit(): void {
        isTicketsActive.set(true);
    }

    handlePageEvent(e: PageEvent) {
        if (this.destination !== "") {
            this.routeService.getAllRoutesByDestination(e.pageIndex, this.destination)
                .subscribe(x => {
                    this.ticket.set(x);
                    this.length = x.totalRoutes;
                });
        }

        if (this.destination === "") {
            this.routeService.getAllRoutesPaginated(e.pageIndex)
                .subscribe(x => {
                    this.length = x.totalRoutes;
                    this.ticket.set(x);
                });
        }
    }

    searchByDestination() {
        if (this.destination !== '') {
            this.routeService.getAllRoutesByDestination(0, this.destination)
                .subscribe(x => {
                    this.ticket.set(x);
                    this.length = x.totalRoutes;
                });
            console.log(this.destination + "++++++++++++++++++");
        }

        if (this.destination === "") {
            this.routeService.getAllRoutesPaginated(0)
                .subscribe(x => {
                    this.length = x.totalRoutes;
                    this.ticket.set(x);
                });
        }
    }

    buyTicket(routeId: string, price: string) {
        if (confirm(`Want to buy this ticket? for the price of ${price}`)) {
            this.ticketService.buyTicket(routeId).subscribe();
        }
    }

    ngOnDestroy(): void {
        isTicketsActive.set(false);
    }


}
