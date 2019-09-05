import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserListComponent} from './user-list/user-list.component';
import {UserRoutingModule} from './user-routing/user-routing.module';
import {MaterialModule} from './../material/material.module';
import {UserDetailsComponent} from './user-details/user-details.component';
import {UserDataComponent} from './user-details/user-data/user-data.component';
import {DescriptionDataComponent} from './user-details/description-data/description-data.component';
import {UserCreateComponent} from './user-create/user-create.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import {UserUpdateComponent} from "./user-update/user-update.component";

@NgModule({
  declarations: [UserListComponent, UserDetailsComponent, UserDataComponent, DescriptionDataComponent, UserCreateComponent, UserUpdateComponent],
  imports: [
    CommonModule,
    MaterialModule,
    UserRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
