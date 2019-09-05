import {RepositoryService} from './../../shared/repository.service';
import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Location} from '@angular/common';
import {UserRequest} from '../../_interface/userRequest.model';
import {MatDialog} from '@angular/material';
import {SuccessDialogComponent} from "../../shared/dialogs/success-dialog/success-dialog.component";
import {ErrorHandlerService} from "../../shared/error-handler.service";
import {requiredFileType} from "../../shared/util/customValidator";

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})

export class UserCreateComponent implements OnInit {

  public userForm: FormGroup;
  private dialogConfig;
  progress = 0;
  maxUserDescriptionFieldsLength = 500;

  constructor(private location: Location, private repository: RepositoryService, private dialog: MatDialog, private errorService: ErrorHandlerService) { }

  ngOnInit() {
    this.userForm = new FormGroup({
      firstname: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      lastname: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      birthdate: new FormControl(new Date(1988,1,3)),
      email: new FormControl('', [Validators.required, Validators.email, Validators.minLength(8), Validators.maxLength(60)]),
      address: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(60)]),
      image: new FormControl(null, [Validators.required, requiredFileType('png')]),
      description: new FormControl(null,  [Validators.maxLength(this.maxUserDescriptionFieldsLength)]),
      skills: new FormControl(null,  [Validators.maxLength(60)]),
      experience: new FormControl(null,  [Validators.maxLength(60)])
    });

    this.dialogConfig = {
      height: '200px',
      width: '400px',
      disableClose: true,
      data: { }
    };
  }

  public hasError = (controlName: string, errorName: string) =>{
    const control = this.userForm.get(controlName);
    return control.dirty && control.hasError(errorName);
    // return this.userForm.controls[controlName].hasError(errorName);
  };

  public onCancel = () => {
    this.location.back();
  };

  public createUser = (userFormValue) => {
    if (this.userForm.valid) {
      this.executeUserCreation(userFormValue);
    }
  };

  private executeUserCreation = (userFormValue) => {
    let user: UserRequest = {
      id: null,
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
    this.repository.create(apiUrl, user)
      .subscribe(res => {

          this.progress = this.repository.progress;

          let dialogRef = this.dialog.open(SuccessDialogComponent, this.dialogConfig);
          //we are subscribing on the [mat-dialog-close] attribute as soon as we click on the dialog button
          dialogRef.afterClosed()
            .subscribe(result => {
              this.location.back();
            });
        },
        (error => {
          this.errorService.dialogConfig = { ...this.dialogConfig };
          this.errorService.handleError(error);
        })
      )
  }
}
