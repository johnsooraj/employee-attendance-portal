import { Component, OnInit, Input } from '@angular/core';
import { NgbModal, NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from 'src/app/models/employee';
import { CommonService } from 'src/app/services/common.service';
import { AttendanceLogReport } from 'src/app/models/attendanceLogReport';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  singleEmployeeAttendanceData = new AttendanceLogReport();
  employeeData = new Employee();

  constructor(
    private modalService: NgbModal,
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.employeeList
  }

  reportTabChangeEvent(event: NgbTabChangeEvent) {
    console.log(event)
  }

  viewEmployeeDetails(emp: Employee) {
    this.employeeData = emp;
    this.commonService.fetchAttendanceReportForEmployee().subscribe((logData) => {
      this.singleEmployeeAttendanceData = logData;
      this.singleEmployeeAttendanceData.attendanceLog.forEach(data =>{
        data.punchingTime
      })
    });
  }

}
