import { HttpClient, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserGame } from "../models/userGame";

@Injectable({
    providedIn: 'root'
})
export class UserGameService {
    private _url: string = 'http://localhost:8080/api/user-games';
    private _http = inject(HttpClient);

    getAll(): Observable<UserGame[]> {
        return this._http.get<UserGame[]>(this._url);
    }

    getAllByStatus(status: string): Observable<UserGame[]>{
        return this._http.get<UserGame[]>(`${this._url}?status=`+status);
    }

    getAllByStatusForLoggedUser(status: string): Observable<UserGame[]>{
        return this._http.get<UserGame[]>(`${this._url}/logged-user?status=`+status);
    }

    getAllForLoggedUser(): Observable<UserGame[]>{
        return this._http.get<UserGame[]>(`${this._url}/logged-user`);
    }

    getUserGameById(id: number): Observable<UserGame>{
        return this._http.get<UserGame>(`${this._url}/${id}`);
    }

    getUserGameByReviewId(id: number): Observable<UserGame>{
        return this._http.get<UserGame>(`${this._url}/by-review/${id}`);
    }

    createUserGame(userGame: Partial<UserGame>):Observable<UserGame> {
        return this._http.post<UserGame>(this._url, userGame);
    }

    createUserGameForLoggedUser(userGame: Partial<UserGame>):Observable<UserGame> {
        return this._http.post<UserGame>(`${this._url}/logged-user`, userGame);
    }

    updateUserGame(userGame: Partial<UserGame>, id: number): Observable<UserGame>{
        return this._http.put<UserGame>(`${this._url}/${id}`, userGame);
    }

    deleteUserGame(id: number): Observable<void>{
        return this._http.delete<void>(`${this._url}/${id}`);
    }


}