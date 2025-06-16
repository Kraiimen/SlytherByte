import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthMainComponent } from './components/authentication/auth-main/auth-main.component';
import { HomeComponent } from './components/home/home.component';
import { MainContainerComponent } from './components/main-container/main-container.component';
import { ExpiredSessionComponent } from './components/expired-session/expired-session.component';
import { UserGamesComponent } from './components/user-profile/user-games/user-games.component';
import { UserProfileContentComponent } from './components/user-profile/user-profile-content/user-profile-content.component';

export const routes: Routes = [
    { path: '', redirectTo: 'auth', pathMatch: 'full' },
    { path: 'auth', component: AuthMainComponent },
    {
        path: 'app',
        component: MainContainerComponent,
        children: [
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            { path: 'home', component: HomeComponent },
            { path: 'expired-session', component: ExpiredSessionComponent }
        ]
    },
    {
        path: 'user-profile', component: UserProfileComponent,
        children: [
            { path: '', component: UserProfileContentComponent, pathMatch: 'full' },
            { path: 'user-games/:status', component: UserGamesComponent }
        ]
    },

];
