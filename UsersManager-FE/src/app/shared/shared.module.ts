import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MaterialModule} from '../material/material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {SuccessDialogComponent} from './dialogs/success-dialog/success-dialog.component';
import {ErrorDialogComponent} from './dialogs/error-dialog/error-dialog.component';
import {ConfirmDialogComponent} from './dialogs/confirm-dialog/confirm-dialog.component';
import {FileCreateUploadComponent} from "../user/user-create/file-upload/file-create-upload.component";
import {CreateProgressComponent} from "../user/user-create/progress/create-progress.component";
import {UpdateProgressComponent} from "../user/user-update/progress/update-progress.component";
import {FileUpdateUploadComponent} from "../user/user-update/file-upload/file-update-upload.component";

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
  ],
  exports: [
    MaterialModule,
    FlexLayoutModule,
    SuccessDialogComponent,
    ConfirmDialogComponent,
    ErrorDialogComponent,
    FileCreateUploadComponent,
    FileUpdateUploadComponent,
    CreateProgressComponent,
    UpdateProgressComponent
  ],
  entryComponents: [
    SuccessDialogComponent,
    ConfirmDialogComponent,
    ErrorDialogComponent,
    FileCreateUploadComponent,
    FileUpdateUploadComponent,
    CreateProgressComponent,
    UpdateProgressComponent
  ],
  declarations: [
    SuccessDialogComponent,
    ConfirmDialogComponent,
    ErrorDialogComponent,
    ConfirmDialogComponent,
    FileCreateUploadComponent,
    FileUpdateUploadComponent,
    CreateProgressComponent,
    UpdateProgressComponent
  ]
})
export class SharedModule { }
