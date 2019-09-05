import {Component, Input, OnInit} from '@angular/core';
import {UserDetailsResponse} from "../../../_interface/userDetailsResponse.model";

@Component({
  selector: 'app-description-data',
  templateUrl: './description-data.component.html',
  styleUrls: ['./description-data.component.css']
})
export class DescriptionDataComponent implements OnInit {
  @Input() public user: UserDetailsResponse;

  constructor() { }

  ngOnInit() {
  }

}
