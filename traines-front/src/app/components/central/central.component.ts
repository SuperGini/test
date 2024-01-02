import {Component} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {RouterOutlet} from "@angular/router";

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

}
