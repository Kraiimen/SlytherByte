import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Publisher } from "../models/publisher";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root"
})

export class PublisherService{
    private _url ='http://localhost:8080/api/publishers';
    private _http = inject(HttpClient);

    getPublishers(): Observable<Publisher[]>{
        return this._http.get<Publisher[]>(this._url);
    }

}