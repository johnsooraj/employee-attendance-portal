import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Employee } from 'src/app/models/employee';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentTime: Date = new Date();
  pageNumber = 0;
  pageSize = 0;
  pageTotal = 0;

  constructor(
    private commonService: CommonService,
    private modalService: NgbModal
  ) {
    setInterval(() => {
      this.currentTime = new Date();
    }, 1);
  }

  ngOnInit() {
    this.commonService.fetchAllEmployees(0, 100).subscribe((data) => {
      this.commonService.employeeList = data;
    });
  }

  removeEMployee(empdata: Employee, content: any) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      size: 'lg',
      centered: false
    }).result.then((result) => {
      if (result) {
        this.commonService.removeEmployee(empdata.id, result).subscribe((data) => {
          if (data.loggingStatus) {
            this.commonService.employeeList.forEach((employee) => {
              const index = this.commonService.employeeList.findIndex(obj => obj.id === empdata.id);
              if (index !== -1) {
                this.commonService.employeeList.splice(index, 1);
              }
            });
          } else {
            window.alert(data.message);
          }
        }, (error) => {
          window.alert(error.message);
        });
      }
    }, (reason) => {
      console.log(reason, 'reason')
    });
  }




}
