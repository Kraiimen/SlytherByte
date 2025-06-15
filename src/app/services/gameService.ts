import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { EMPTY, Observable } from "rxjs";
import { Game } from "../models/game";
import { Filter } from "../models/filter";

@Injectable({
    providedIn: 'root'
})
export class GameService{
    private _url: string = 'http://localhost:8080/api/games'
    private _http = inject(HttpClient);

    searchGames(filters: Partial<Filter>): Observable<Game[]>{
        const result = this._http.post<Game[]>(`${this._url}/filters`, JSON.stringify(filters), {
            headers: new HttpHeaders({"Content-Type": "application/json"})
        });
        return result;
    }
    
    findGameById(id: number): Observable<Game> {
        return this._http.get<Game>(`${this._url}/${id}`);
    }

    updateGame(gameToUpdate: Partial<Game>): Observable<Game>{
        return this._http.post<Game>(`${this._url}`, gameToUpdate);
    }

    addGame(newGame: Game): Observable<Game>{
        return this._http.post<Game>(`${this._url}`,newGame);
    }

    delete(id: number): void{
        this._http.delete<Game>(`${this._url}/${id}`)
    }
}