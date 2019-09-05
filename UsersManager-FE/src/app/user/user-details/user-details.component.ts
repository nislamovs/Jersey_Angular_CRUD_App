import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RepositoryService} from './../../shared/repository.service';
import {ErrorHandlerService} from './../../shared/error-handler.service';
import {UserDetailsResponse} from "../../_interface/userDetailsResponse.model";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  public user: UserDetailsResponse;
  public showDescription;

  constructor(private repository: RepositoryService, private router: Router,
              private activeRoute: ActivatedRoute, private errorHandler: ErrorHandlerService) { }

  ngOnInit() {
    this.getUserDetails();
    this.repository.getUsersCount();
  }

  private getUserDetails = () =>{
    let id: string = this.activeRoute.snapshot.params['id'];
    let apiUrl: string = `users/${id}`;

    this.repository.getData(apiUrl)
      .subscribe(res => {
          this.user = res as UserDetailsResponse;
        },
        (error) =>{
          this.errorHandler.handleError(error);
        })
  };

  private prevUser = () => {
    let currId: number = Number(this.activeRoute.snapshot.params['id']);
    if (currId > 1) {
      let id: string = String(currId - 1);
      let apiUrl: string = `users/${id}`;

      this.repository.getData(apiUrl)
        .subscribe(res => {
            this.user = res as UserDetailsResponse;
            this.router.navigate([this.router.url.replace(this.activeRoute.snapshot.params['id'], id)]);
          },
          (error) => {
            this.errorHandler.handleError(error);
          })
    }
  };

  private nextUser = () => {
    let currId: number = Number(this.activeRoute.snapshot.params['id']);
    let usersCount: number = this.repository.getUsersCount();
    if (currId < usersCount) {

      let id: string = String(currId + 1);
      let apiUrl: string = `users/${id}`;

      this.repository.getData(apiUrl)
        .subscribe(res => {
            this.user = res as UserDetailsResponse;
            this.router.navigate([this.router.url.replace(this.activeRoute.snapshot.params['id'], id)]);
          },
          (error) => {
            this.errorHandler.handleError(error);
          })
    }
  };
}
