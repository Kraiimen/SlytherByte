import { Component, inject, OnInit } from '@angular/core';
import { UserProfileSideBarComponent } from './user-profile-side-bar/user-profile-side-bar.component';
import { UserProfileContentComponent } from './user-profile-content/user-profile-content.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  imports: [UserProfileSideBarComponent, RouterOutlet],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {

}
