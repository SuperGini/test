import {Component} from "@angular/core";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: "main-component",
  templateUrl: "main.html",
  styleUrl: "main.css",
  imports: [
    NgOptimizedImage
  ],
  standalone: true
})
export class MainComponent {

}
