import {Component, Inject, OnDestroy, OnInit, PLATFORM_ID, signal} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {TicketsService} from "../../../services/tickets/tickets.service";
import {TicketResponsePaginated} from "../../../dto/response/ticket.response.paginated";
import {isPlatformBrowser} from "@angular/common";
import {isTicketsActive} from "../../../state/state";
import {Router} from "@angular/router";

@Component({
    selector: "tickets-component",
    templateUrl: "tickets.html",
    styleUrl: "tickets.css",
    imports: [
        MatIconModule,
        MatPaginatorModule
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

    public ticket = signal<TicketResponsePaginated>(null);

    isBrowser: boolean;

    constructor(private ticketService: TicketsService,
                @Inject(PLATFORM_ID) platformId: Object,
    ) {
        this.isBrowser = isPlatformBrowser(platformId);
        if (this.isBrowser) {

            this.ticketService.getAllTicketsPaginated(0)
                .subscribe(x => {
                    this.ticket.set(x);
                    // this.ticket = x;
                    this.length = x.totalTickets;
                    console.log(x.ticketResponses);
                    console.log(x.totalTickets);
                });
        }
    }

    ngOnInit(): void {
        isTicketsActive.set(true);
    }

    handlePageEvent(e: PageEvent) {
        // if (this.isBrowser) {
        this.ticketService.getAllTicketsPaginated(e.pageIndex)
            .subscribe(x => {
                this.length = x.totalTickets;
                this.ticket.set(x);
            });
    }

    ngOnDestroy(): void {
        isTicketsActive.set(false);
    }

}
