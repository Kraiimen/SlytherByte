import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { GameMedia } from "../models/gameMedia";

@Injectable({ providedIn: 'root' })
export class GameMediaService {
    private _url = 'http://localhost:8080/api/game-media';
    private _http = inject(HttpClient);

    getTrailerByGameId(id: number): Observable<GameMedia> {
        return this._http.get<GameMedia>(`${this._url}/trailer/${id}`);
    }

    getImagesByGameId(id: number): Observable<GameMedia[]> {
        return this._http.get<GameMedia[]>(`${this._url}/${id}`);
    }
}