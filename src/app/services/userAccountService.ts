import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserProfile } from "../models/userProfile";
import { UserAccount } from "../models/userAccount";
import { DataService } from "./dataService";

@Injectable({
    providedIn: 'root'
})
export class UserAccountService {
    private _url = 'http://localhost:8080/api/user-accounts'
    private _http = inject(HttpClient);

    getById(id: number): Observable<UserAccount> {
        return this._http.get<UserAccount>(`${this._url}/${id}`);
    }
}