import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Platform } from "../models/platform";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class PlatformService{
    private _url = 'http://localhost:8080/api/platforms';
    private _http = inject(HttpClient);

    getPlatforms(): Observable<Platform[]>{
        return this._http.get<Platform[]>(this._url);
    }
}