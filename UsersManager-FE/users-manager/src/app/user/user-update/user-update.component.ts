import { RepositoryService } from './../../shared/repository.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {DatePipe, Location} from '@angular/common';
import { UserRequest } from '../../_interface/userRequest.model';
import {DateAdapter, MAT_DATE_FORMATS, MatDialog} from '@angular/material';
import {SuccessDialogComponent} from "../../shared/dialogs/success-dialog/success-dialog.component";
import {ErrorHandlerService} from "../../shared/error-handler.service";
import {requiredFileType} from "../../shared/util/customValidator";
import {UserDetailsResponse} from "../../_interface/userDetailsResponse.model";
import {ActivatedRoute, Router} from "@angular/router";
import {APP_DATE_FORMATS, AppDateAdapter} from "./format-datepicker";

@Component({
  selector: 'app-user-create',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css'],
  providers: [
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]
})

export class UserUpdateComponent implements OnInit {

  public userForm: FormGroup;
  private dialogConfig;
  maxUserDescriptionFieldsLength = 500;
  public user: UserDetailsResponse;

  constructor(private location: Location, private repository: RepositoryService, private activeRoute: ActivatedRoute,
              private dialog: MatDialog, private errorHandler: ErrorHandlerService) { }

  ngOnInit() {

    this.dialogConfig = {
      height: '200px',
      width: '400px',
      disableClose: true,
      data: { }
    };

    this.userForm = new FormGroup({
      id: new FormControl(''),
      firstname: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      lastname: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      birthdate: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.minLength(8), Validators.maxLength(60)]),
      address: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(60)]),
      image: new FormControl('', [Validators.required, requiredFileType('png')]),
      description: new FormControl('',  [Validators.maxLength(this.maxUserDescriptionFieldsLength)]),
      skills: new FormControl('',  [Validators.maxLength(60)]),
      experience: new FormControl('',  [Validators.maxLength(60)])
    });

    this.getUserDetails();
    this.repository.getUsersCount();

  }

  public hasError = (controlName: string, errorName: string) =>{
    const control = this.userForm.get(controlName);
    // if(controlName==='firstname' && errorName=='required') {
    //   console.log(">>>   " + controlName + " >>>   dirty:" + control.dirty + ",  hasError: " + control.hasError(errorName));
    // }
    return control.dirty && control.hasError(errorName);
    // return control.hasError(errorName);
  };

  public onCancel = () => {
    this.location.back();
  };

  public updateUser = (userFormValue) => {
    if (this.userForm.valid) {
      this.executeUserUpdate(userFormValue);
    }
  };

  private getUserDetails = () =>{
    let id: string = this.activeRoute.snapshot.params['id'];
    let apiUrl: string = `users/${id}`;

    this.repository.getData(apiUrl)
      .subscribe(res => {
          this.user = res as UserDetailsResponse;
          this.userForm.controls['id'].patchValue(this.user.id);
          this.userForm.controls['firstname'].patchValue(this.user.firstname);
          this.userForm.controls['lastname'].patchValue(this.user.lastname);

          this.userForm.controls['birthdate'].patchValue(
            new Date(
              new Date(this.user.birthdate).getFullYear(),
              new Date(this.user.birthdate).getMonth(),
              new Date(this.user.birthdate).getDay()
            )
          );

          this.userForm.controls['email'].patchValue(this.user.email);
          this.userForm.controls['address'].patchValue(this.user.address);
          this.userForm.controls['phone'].patchValue(this.user.phone);
          this.userForm.controls['description'].patchValue(this.user.description);
          this.userForm.controls['skills'].patchValue(this.user.skills);
          this.userForm.controls['experience'].patchValue(this.user.experience);
          this.userForm.controls['image'].patchValue(this.user.photoImage);
        },
        (error) => {
          this.errorHandler.handleError(error);
        })
  };

  private executeUserUpdate = (userFormValue) => {
    let user: UserRequest = {
      id: userFormValue.id,
      firstname: userFormValue.firstname,
      lastname: userFormValue.lastname,
      birthdate: userFormValue.birthdate,
      email: userFormValue.email,
      address: userFormValue.address,
      phone: userFormValue.phone,
      image: userFormValue.image,
      description: userFormValue.description,
      skills: userFormValue.skills,
      experience: userFormValue.experience
    };

    let apiUrl = 'users';
    this.repository.update(apiUrl, user)
      .subscribe(res => {
          let dialogRef = this.dialog.open(SuccessDialogComponent, this.dialogConfig);

          //we are subscribing on the [mat-dialog-close] attribute as soon as we click on the dialog button
          dialogRef.afterClosed()
            .subscribe(result => {
              this.location.back();
            });
        },
        (error => {
          this.errorHandler.dialogConfig = { ...this.dialogConfig };
          this.errorHandler.handleError(error);
        })
      )
  }

}
