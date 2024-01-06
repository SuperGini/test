import {Component} from "@angular/core";
import {NgOptimizedImage} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import redirect from "../../util/redirect";


@Component({
  selector: "main-component",
  templateUrl: "main.html",
  styleUrl: "main.css",
    imports: [
        NgOptimizedImage,
        MatIconModule,
        MatInputModule,
    ],
  standalone: true
})
export class MainComponent {

    redirect() {
        window.location.href = redirect();
        console.log("redirect ----" + redirect());
    }


}
