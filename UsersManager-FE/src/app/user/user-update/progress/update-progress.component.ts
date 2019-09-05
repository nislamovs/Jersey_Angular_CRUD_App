import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-update-progress',
  templateUrl: './update-progress.component.html',
})
export class UpdateProgressComponent {
  @Input() progress = 0;

}
