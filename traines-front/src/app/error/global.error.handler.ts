import {ErrorHandler} from "@angular/core";
/**
 * https://dev.to/this-is-angular/angular-error-handling-101-553
 * */
export class GlobalErrorHandler implements ErrorHandler{

    handleError(error: any): void {


        throw new Error("Method not implemented.");
    }

}
