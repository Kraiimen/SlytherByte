import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { RegisterRequest } from "../models/registerRequest";
import { AuthenticationRequest } from "../models/authenticationRequest";
import { AuthenticationResponse } from "../models/authenticationResponse";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private _url = 'http://localhost:8080/api/auth/register';
    private _http = inject(HttpClient);
    
    public sendRegisterRequest(obj: RegisterRequest): Observable<void> {
        return this._http.post<void>(this._url, obj);
    }

    public sendLoginRequest(obj: AuthenticationRequest): Observable<AuthenticationResponse> {
        return this._http.post<AuthenticationResponse>('http://localhost:8080/api/auth/login', obj);
    }
}