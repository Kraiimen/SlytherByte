import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { CatalogueComponent } from './catalogue/catalogue.component';

export const routes: Routes = [
    {path: 'user-profiles/:id', component: UserProfileComponent},
    {path: 'games', component: CatalogueComponent}
];


