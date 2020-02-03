import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from './services/common.service';
import { Employee } from './models/employee';
import { Observable } from 'rxjs';
import { CustomHttpResponse } from './models/httpResponse';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'angular-portal-app';

  newEmployee = new Employee();

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

  saveEmployeeSubmit() {
    this.commonService.saveEmployee(this.newEmployee).subscribe((emp) => {
      this.commonService.employeeList.unshift(emp);
      this.newEmployee = new Employee();
      this.modalService.dismissAll();
    });
  }


}
