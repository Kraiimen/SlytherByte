import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { EMPTY, Observable } from "rxjs";
import { Game } from "../models/game";

@Injectable({
    providedIn: 'root'
})
export class GameService{
    private _url: string = 'http://localhost:8080/api/games'
    private _http = inject(HttpClient);

    getGames(filters: Partial<Game>): Observable<Game[]>{
        const result = this._http.post<Game[]>(`${this._url}`, JSON.stringify(filters));
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