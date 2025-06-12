import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { RegisterRequest } from "../models/registerRequest";
import { ResponseErrors } from "../models/responseErrors";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private _url = 'http://localhost:8080/api/auth/register';
    private _http = inject(HttpClient);
    
    public sendRegisterRequest(obj: RegisterRequest): Observable<void | ResponseErrors> {
        return this._http.post<void | ResponseErrors>(this._url, obj);
    }
}