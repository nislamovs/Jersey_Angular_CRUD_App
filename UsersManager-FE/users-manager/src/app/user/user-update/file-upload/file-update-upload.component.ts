import {Component, ElementRef, EventEmitter, HostListener, Injectable, Input, OnInit, Output} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Globs } from './Globs';
import {isBase64} from "../../../shared/repository.service";

@Component({
  selector: 'app-file-update-upload',
  templateUrl: './file-update-upload.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: FileUpdateUploadComponent,
      multi: true
    }
  ],
  styleUrls: ['./file-update-upload.component.css']
})
export class FileUpdateUploadComponent implements ControlValueAccessor{//}, OnInit {
  @Input() progress;
  onChange: Function;

  @Input() file: File | null = null;

  public imagePath;
  @Input() imgURL: any;
  public message: string;

  @HostListener('change', ['$event.target.files']) emitFiles( event: FileList ) {
    const file = event && event.item(0);
    this.onChange(file);
    this.file = file;
  }

  constructor( private host: ElementRef<HTMLInputElement> ) {
  }

  writeValue( value: null ) {
    // clear file input
    this.host.nativeElement.value = '';
    this.file = null;
  }

  registerOnChange( fn: Function ) {
    this.onChange = fn;
  }

  registerOnTouched( fn: Function ) {
  }

  preview(files) {
    if (files.length === 0) {
      return;
    }
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }

    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
      Globs.imgSRC = reader.result;
    };

  }

  isEncoded(image) {
    return image !== undefined && isBase64(image);
  }

}
