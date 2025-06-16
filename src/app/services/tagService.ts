import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Tag } from "../models/tag";

@Injectable({
    providedIn : "root"
}) 

export class TagService{
    private _url = 'http://localhost:8080/api/tags';
    private _http = inject(HttpClient);

    getTags(): Observable<Tag[]>{
        return this._http.get<Tag[]>(this._url);
    }

} 