import { HttpClient } from "@angular/common/http";
import { inject, Inject, Injectable } from "@angular/core";
import { Review } from "../models/review";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class ReviewService{
    private _url: string = 'http://localhost:8080/api/reviews';
    private _http = inject(HttpClient);

    getAll(): Observable<Review[]> {
            return this._http.get<Review[]>(this._url);
        }
}