import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { UserAccount } from "../models/userAccount";

@Injectable({ providedIn: 'root' })
export class DataService {
    private _loggedUser = new BehaviorSubject<UserAccount | null>(null);
    loggedUser$ = this._loggedUser.asObservable();

    get loggedUser(): UserAccount | null{ 
        return this._loggedUser.getValue();
    }
    setLoggedUser(user: UserAccount): void {
        this._loggedUser.next(user);
        console.log("Logged user set:");
        console.log(user);
    }

    clearData(): void {
        this._loggedUser.next(null);
    }
}