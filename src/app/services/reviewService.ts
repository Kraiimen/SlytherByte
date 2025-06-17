import { HttpClient } from "@angular/common/http";
import { inject, Inject, Injectable } from "@angular/core";
import { Review } from "../models/review";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class ReviewService {
    private _url: string = 'http://localhost:8080/api/reviews';
    private _http = inject(HttpClient);

    getAll(): Observable<Review[]> {
        return this._http.get<Review[]>(this._url);
    }

    getReviewById(id: number): Observable<Review> {
        return this._http.get<Review>(`this._url/${id}`);
    }

    createReview(review: Partial<Review>): Observable<Review>{
        return this._http.post<Review>(this._url, review);
    }

    updateReview(review: Partial<Review>, reviewid: number): Observable<Review>{
        return this._http.put<Review>(`${this._url}/${reviewid}`, review);
    }

    deleteReview(reviewId: number): Observable<void>{
        return this._http.delete<void>(`this.url/${ reviewId }`);
    }

}