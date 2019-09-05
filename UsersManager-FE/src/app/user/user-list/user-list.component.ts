import {RepositoryService} from './../../shared/repository.service';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {ErrorHandlerService} from '../../shared/error-handler.service';
import {UserResponse} from '../../_interface/userResponse.model';
import {Router} from '@angular/router';
import {ConfirmDialogComponent} from "../../shared/dialogs/confirm-dialog/confirm-dialog.component";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, AfterViewInit {

  public displayedColumns = ['id', 'firstname', 'lastname', 'birthdate', 'email', 'phone', 'address', 'photoIcon', 'details', 'update', 'delete'];
  data;
  public dataSource = new MatTableDataSource<UserResponse>();
  private dialogConfig;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private repoService: RepositoryService, private errorService: ErrorHandlerService, private dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.getAllUsers();

    this.dialogConfig = {
      height: '250px',
      width: '400px',
      disableClose: true,
      data: { }
    };
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  public pageChanged = () => {
    this.repoService.getData('users?PageSize='+this.dataSource.paginator.pageSize+'&PageNumber='+(this.dataSource.paginator.pageIndex+1)+'&OrderDirection=asc&OrderBy=id')
      .subscribe(res => {
        this.dataSource._pageData(res as UserResponse[]);
      },(error) => {
      this.errorService.handleError(error);
    })
  };

  public getAllUsers = () => {
    this.repoService.getData('users/all')
      .subscribe(res => {
        this.dataSource.data = res as UserResponse[];
        this.data = res as UserResponse[];
      },(error) => {
        this.errorService.handleError(error);
      })
  };

  public deleteuser = (id: string) => {
    let url: string = `users/${id}`;
    this.repoService.delete(url)
      .subscribe(res => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id != id);
      },(error) => {
        this.errorService.handleError(error);
      })
  };

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  };

  public redirectToDetails = (id: string) => {
    let url: string = `/user/details/${id}`;
    this.router.navigate([url]);
  };

  public redirectToUpdate = (id: string) => {
    let url: string = `/user/update/${id}`;
    this.router.navigate([url]);
  };

  public redirectToDelete = (element: any) => {

    this.dialogConfig.data = {
      'userName': element.firstname + ' ' + element.lastname,
      'userId': element.id,
    };

    let dialogRef = this.dialog.open(ConfirmDialogComponent, this.dialogConfig);
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result) {
          this.deleteuser(this.dialogConfig.data.userId);
        }
      });
  }

}
