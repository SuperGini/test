import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

@Component({
    selector: "redirect-component",
    templateUrl: "redirect.html",
    styleUrl: "redirect.css",
    standalone: true

})

export class AuthComponent implements OnInit {

    constructor(private activatedRoute: ActivatedRoute, private authService: AuthService) {
        this.getAuthCodeFromUrl();
    }

    ngOnInit(): void {
        this.authService.getAuthToken();
    }

    private getAuthCodeFromUrl() {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params?.['code']) {
                this.authService.code = params['code'];
                console.log(`This is the auth code: ${this.authService.code}`);
            }
        });
    }


}
