import {Component} from "@angular/core";
import {NgOptimizedImage} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";

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


}
