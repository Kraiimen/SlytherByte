import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserProfile } from "../models/userProfile";

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {
    private _url: string = 'http://localhost:8080/api/user-profiles'

    constructor(private http: HttpClient) {
    }

    getUserProfiles(): Observable<UserProfile[]> {
        return this.http.get<UserProfile[]>(this._url);
    }

    getUserProfileById(id: number): Observable<UserProfile> {
        return this.http.get<UserProfile>(`${this._url}/${id}`);
    }

    getLoggedUserProfile(): Observable<UserProfile> {
        return this.http.get<UserProfile>(`${this._url}/logged-user`);
    }

    createUserProfile(userProfile: Partial<UserProfile>): Observable<UserProfile> {
        return this.http.post<UserProfile>(this._url, userProfile);
    }

    updateUserProfile(userProfile: UserProfile): Observable<UserProfile> {
        return this.http.put<UserProfile>(this._url, userProfile);
    }



}