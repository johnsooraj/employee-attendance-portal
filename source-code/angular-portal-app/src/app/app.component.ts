import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from './services/common.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-portal-app';

  constructor(
    private modalService: NgbModal,
    private commonService: CommonService
  ) {

  }

  addNewEmployeeEvent(content: any) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      size: 'lg',
      centered: false
    }).result.then((result) => {

    }, (reason) => {

    });
  }
}
