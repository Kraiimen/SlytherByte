import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Game } from "../models/game";

@Injectable({
    providedIn: 'root'
})
export class GameService{
    private _url: string = 'http://localhost:8080/api/games'
    private _http = inject(HttpClient);

    findGameById(id: number): Observable<Game> {
        return this._http.get<Game>(`${this._url}/${id}`);
    }

    
}