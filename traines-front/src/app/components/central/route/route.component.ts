import {Component, inject, OnDestroy, OnInit} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CreateRoute} from "../../../dto/request/create.route";
import {RouteService} from "../../../services/route/route.service";
import {isRouteActive} from "../../../state/state";

@Component({
    selector: "route-component",
    templateUrl: "route.html",
    styleUrl: "route.css",
    imports: [
        MatIconModule,
        ReactiveFormsModule
    ],
    standalone: true
})

export class RouteComponent implements OnInit, OnDestroy {

    public routeForm: FormGroup;
    private formBuilder = inject(FormBuilder);
    private routeService = inject(RouteService);

    ngOnInit(): void {
        this.routeForm = this.formBuilder.group({
            departure: ['', Validators.required],
            arrival: ['', Validators.required],
            price: ['', Validators.required]
        });

        isRouteActive.set(true);
    }

    createRoute() {
        const createRoute: CreateRoute = this.createRouteFromForm();
        this.routeService.createRoute(createRoute)
            .subscribe();
    }

    private createRouteFromForm(): CreateRoute {
        const form = this.routeForm.value;
        return {
            startLocation: form.departure,
            endLocation: form.arrival,
            price: form.price
        };
    }

    ngOnDestroy(): void {
       isRouteActive.set(false);
    }


}
