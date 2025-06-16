import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Language } from "../models/language";

@Injectable({
    providedIn: "root"
})

export class LanguageService{
    private _url = 'http://localhost:8080/api/languages';
    private _http = inject(HttpClient);

    getLanguages(): Observable<Language[]>{
        return this._http.get<Language[]>(this._url);
    }

}