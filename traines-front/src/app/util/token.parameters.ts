import {HttpHeaders, HttpParams} from "@angular/common/http";


export const httpParams = (code: string): HttpParams => {
    return  new HttpParams()
        .append("client_id", "client")
        .append("redirect_uri", "http://localhost:8083/authorized")
        .append("grant_type", "authorization_code")
        .append("code", code)
        .append("code_verifier", "qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI");
};

export const httpHeaders = (): HttpHeaders => {
    return new HttpHeaders()
        .append("Authorization", generateBasicAuthForClient())
        .append("content-type", "application/json");
};


const generateBasicAuthForClient = (): string => {
    const client = 'client';
    const secret = 'secret';
    return `Basic ` + btoa(`${client}:${secret}`);
};






