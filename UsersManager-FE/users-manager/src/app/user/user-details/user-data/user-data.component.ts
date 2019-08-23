import { UserResponse } from '../../../_interface/userResponse.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {UserRequest} from "../../../_interface/userRequest.model";
import {UserDetailsResponse} from "../../../_interface/userDetailsResponse.model";

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {
  @Input() public user: UserDetailsResponse;
  public selectOptions = [{name:'Show', value: 'show'}, {name: `Don't Show`, value: ''}];
  @Output() selectEmitt = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  public onChange = (event) => {
    this.selectEmitt.emit(event.value);
  }

}
