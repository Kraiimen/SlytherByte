import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export function httpInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>>{
    console.log("HTTP Request Interceptor");
    console.log("Request URL:", req.url);

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
    
    return next(req);
}
