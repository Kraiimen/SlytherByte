import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthMainComponent } from './components/authentication/auth-main/auth-main.component';
import { HomeComponent } from './components/home/home.component';
import { CatalogueComponent } from './catalogue/catalogue.component';

export const routes: Routes = [
    { path: '', redirectTo: 'auth', pathMatch: 'full' },
    { path: 'auth', component: AuthMainComponent },
    { path: 'home', component: HomeComponent },
    { path: 'user-profiles/:id', component: UserProfileComponent },
    {path: 'games', component: CatalogueComponent}
];


