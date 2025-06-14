import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, throwError } from "rxjs";

export function httpInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>>{
    const router = inject(Router);
    
    if (req.url.includes('/api/auth/login') || req.url.includes('/api/auth/register')) {
        console.log("Skipping token for authentication requests");
        return next(req);
    }

    const token = localStorage.getItem('token');
    if (token) {
        req = req.clone({
            headers: req.headers.append('Authorization', `Bearer ${token}`)
        });
    } else {
        console.warn("No token found, request will not have Authorization header");
    }

    return next(req).pipe(
        catchError(error => {
            if (error.status === 401 || error.status === 403) {
                router.navigate(['/app/expired-session']);
            }
            return throwError(() => error);
        })
    );
}
