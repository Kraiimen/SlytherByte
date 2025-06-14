import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthMainComponent } from './components/authentication/auth-main/auth-main.component';
import { HomeComponent } from './components/home/home.component';
import { CatalogueMainComponent } from './components/catalogue/catalogue-main/catalogue-main.component';
import { MainContainerComponent } from './components/main-container/main-container.component';
import { ExpiredSessionComponent } from './components/expired-session/expired-session.component';

export const routes: Routes = [
    { path: '', redirectTo: 'auth', pathMatch: 'full' },
    { path: 'auth', component: AuthMainComponent },
    { path: 'home', component: HomeComponent },
    { path: 'user-profiles/:id', component: UserProfileComponent },
    { path: 'games', component: CatalogueMainComponent },
    { 
        path: 'app', 
        component: MainContainerComponent, 
        children: [
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            { path: 'home', component: HomeComponent },
            { path: 'expired-session', component: ExpiredSessionComponent}
        ]
    }
];


