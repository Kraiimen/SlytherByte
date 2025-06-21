import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Store } from "../models/store";

@Injectable({
    providedIn: "root"
})

export class StoreService{
    private _url = 'http://localhost:8080/api/stores';
    private _http = inject(HttpClient);

    getStores(): Observable<Store[]>{
        return this._http.get<Store[]>(this._url);
    }

    getStoresByGameId(id: number): Observable<Store[]>{
        return this._http.get<Store[]>(`${this._url}/by-game/${id}`);
    }
}