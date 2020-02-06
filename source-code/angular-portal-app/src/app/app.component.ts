import { Component } from '@angular/core';
import { Router, ActivatedRoute, ActivationEnd, NavigationStart } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from './models/employee';
import { CommonService } from './services/common.service';
import { filter, map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'angular-portal-app';

  newEmployee = new Employee();
  viewBreadcrumb = false;

  constructor(
    private modalService: NgbModal,
    private commonService: CommonService,
    private router: Router,
    private activeRoute: ActivatedRoute
  ) {
    this.router.events.pipe(
      filter(e => (e instanceof NavigationStart))
    ).subscribe((navURL: NavigationStart) => {
      navURL.url == '/attendance' ? this.viewBreadcrumb = false : this.viewBreadcrumb = true;
    });
  }

  openNgbModal(content: any, scrollable: boolean) {
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
