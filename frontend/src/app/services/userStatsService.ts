import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Top5Tags } from "../models/top5Tags";

@Injectable({
    providedIn: 'root'
})

export class UserStatsService {
    private _url: string = 'http://localhost:8080/api/user-games';
    private _url2: string = 'http://localhost:8080/api/reviews';
    private _http = inject(HttpClient);
    public sources = [
        this._http.get<Top5Tags[]>(`${this._url}/tags/top5`),
        this._http.get<number>(`${this._url}/owned/count`),
        this._http.get<number>(`${this._url}/playing`),
        this._http.get<number>(`${this._url}/beaten`),
        this._http.get<number>(`${this._url}/reviews/count`)
    ];


    getTop5Tags(): Observable<Top5Tags[]> {
        return this._http.get<Top5Tags[]>(`${this._url}/tags/top5`);
    }

    getNumberGamesOwned(): Observable<number> {
        return this._http.get<number>(`${this._url}/owned/count`);
    }

    getNumberGamesPlaying(): Observable<number> {
        return this._http.get<number>(`${this._url}/playing`);
    }

    getNumberGamesBeaten(): Observable<number> {
        return this._http.get<number>(`${this._url}/beaten`);
    }

    getNumberReviews(): Observable<number> {
        return this._http.get<number>(`${this._url}/reviews/count`);
    }
    
    getCountReviews(): Observable<number> {
        return this._http.get<number>(`${this._url2}/logged-user/reviews-count`);
    }

}