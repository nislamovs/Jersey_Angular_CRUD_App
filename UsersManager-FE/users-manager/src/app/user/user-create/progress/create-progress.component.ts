import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-create-progress',
  templateUrl: './create-progress.component.html',
})
export class CreateProgressComponent {
  @Input() progress = 0;

}
