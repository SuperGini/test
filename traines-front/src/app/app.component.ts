import {Component, inject, OnInit, PLATFORM_ID} from '@angular/core';
import {CommonModule, isPlatformBrowser} from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {IconsService} from "./services/svg/icons.service";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

    private iconsService = inject(IconsService);
    private platformId = inject(PLATFORM_ID);

    static isBrowser = new BehaviorSubject<boolean>(null);

    ngOnInit(): void {
       this.iconsService.registerIcons();
       AppComponent.isBrowser.next(isPlatformBrowser(this.platformId));
    }

}
