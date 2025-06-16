import { HttpClient } from "@angular/common/http";
import { inject, Inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Developer } from "../models/developer";

@Injectable({
    providedIn: "root"
})
export class DeveloperService{
    private _url = 'http://localhost:8080/api/developers';
    private _http = inject(HttpClient);

    getDevelopers(): Observable<Developer[]>{
        return this._http.get<Developer[]>(this._url);
    }
}