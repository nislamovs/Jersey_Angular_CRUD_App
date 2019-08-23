import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpEventType, HttpHeaders, HttpResponse} from '@angular/common/http';
import { environment } from './../../environments/environment';
import {FormGroup} from "@angular/forms";
import { pipe } from 'rxjs';
import { filter, map, tap } from 'rxjs/operators';
import {SuccessDialogComponent} from "./dialogs/success-dialog/success-dialog.component";
import {UserCount} from "../_interface/userCount.model";
import {Globs} from "../user/user-create/file-upload/Globs";

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  progress = 0;
  public usersCount;
  success = false;

  constructor(private http: HttpClient) { }

  public getUsersCount = () => {
    if (this.usersCount) {
      return this.usersCount.usersCount;
    } else {
      this.http.get(this.createCompleteRoute("users/count", environment.urlAddress))
        .subscribe(res => {
            this.usersCount = res as UserCount;
          },
          (error) => {
            this.usersCount = undefined;
          });

    }
    return this.usersCount;
  };

  public getData = (route: string) => {
    return this.http.get(this.createCompleteRoute(route, environment.urlAddress));
  };

  public create = (route: string, body) => {
    return this.http.post(this.createCompleteRoute(route, environment.urlAddress), toFormData(body), {
      reportProgress: true,
      observe: 'events'
    }).pipe(
      uploadProgress(progress => (this.progress = progress)),
      toResponseBody()
    );
  };

  public update = (route: string, body) => {
    return this.http.put(this.createCompleteRoute(route, environment.urlAddress), toFormData(body), {
      reportProgress: true,
      observe: 'events'
    }).pipe(
      uploadProgress(progress => (this.progress = progress)),
      toResponseBody()
    );
  };

  public delete = (route: string) => {
    return this.http.delete(this.createCompleteRoute(route, environment.urlAddress));
  };

  private createCompleteRoute = (route: string, envAddress: string) => {
    return `${envAddress}/api/${route}`;
  };

  private generateHeaders = () => {
    return {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
  }
}

export function markAllAsDirty( form: FormGroup ) {
  for ( const control of Object.keys(form.controls) ) {
    form.controls[control].markAsDirty();
  }
}

export function toFormData<T>( formValue: T) {
  const formData = new FormData();

  for ( const key of Object.keys(formValue) ) {
    let value = formValue[key];
    if(key==='birthdate') {
      let current_datetime = new Date(value);
      let day = current_datetime.getDate();
      let month = current_datetime.getMonth()+1;
      let year = current_datetime.getFullYear();
      console.log(">> "+value);
      value = year + '-' + _to2digit(month) + '-' + _to2digit(day);

      console.log(">> "+value);
    }
    if(key==='image') {
        value = new File([value], "image.png", {type: 'image/png'});
         // value = new File([new Blob([value], {type: 'image/png'})],  "file.png", {type: 'image/png'});
    }
    formData.append(key, value);
  }
  //multipart_base64_content
  // formData.append('photo', Globs.imgSRC);
  return formData;
}

export function isBase64(str) {
  try {
    if (str ==='' || str.trim() ===''){ return false; }
    return btoa(atob(str)) == str;
  } catch (err) {
    return false;
  }
}

export function _to2digit(n: number) {
  return ('00' + n).slice(-2);
}

export function uploadProgress<T>( cb: ( progress: number ) => void ) {
  return tap(( event: HttpEvent<T> ) => {
    if ( event.type === HttpEventType.UploadProgress ) {
      cb(Math.round((100 * event.loaded) / event.total));
    }
  });
}

export function toResponseBody<T>() {
  return pipe(
    filter(( event: HttpEvent<T> ) => event.type === HttpEventType.Response),
    map(( res: HttpResponse<T> ) => res.body)
  );
}
