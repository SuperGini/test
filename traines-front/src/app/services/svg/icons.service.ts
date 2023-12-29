import {inject, Injectable} from "@angular/core";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {Icons} from "../../util/icons";

/**
 *  install angular material: npm install --save @angular/material
 * https://medium.com/ngconf/how-to-using-mat-icon-part-two-2dfb748c7bfc
 * How to add SVG
 * */


@Injectable({providedIn: "root"})
export class IconsService {

    private matIconRegistry: MatIconRegistry = inject(MatIconRegistry);
    private domSanitizer: DomSanitizer = inject(DomSanitizer);

    registerIcons(): void {
        Object.values(Icons)
              .forEach(icon =>
                  this.matIconRegistry.addSvgIcon(icon,
                      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/svg/${icon}.svg`))
              );
    }

}
