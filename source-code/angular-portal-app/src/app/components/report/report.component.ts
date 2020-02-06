import { Component, OnInit, Input } from '@angular/core';
import { NgbModal, NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from 'src/app/models/employee';
import { CommonService } from 'src/app/services/common.service';
import { AttendanceLogReport } from 'src/app/models/attendanceLogReport';
import { LogginModal } from 'src/app/models/logginModal';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  singleEmployeeAttendanceData = new AttendanceLogReport();
  employeeData = new Employee();

  staffList = new Array<LogginModal>();

  constructor(
    private modalService: NgbModal,
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.employeeList
  }

  reportTabChangeEvent(event: NgbTabChangeEvent) {
    if (event.nextId == 'avilableEmployees') {
      this.setAvailableEmployeesList();
    }
  }

  viewEmployeeDetails(emp: Employee) {
    this.singleEmployeeAttendanceData = new AttendanceLogReport();
    this.employeeData = emp;
    this.commonService.fetchAttendanceReportForEmployee(emp.id, "2017-01-13T17:09:42.41", "2017-01-13T17:09:42.41").subscribe((logData) => {
      this.singleEmployeeAttendanceData = logData;
      this.singleEmployeeAttendanceData.attendanceLogList.forEach(data => {
        data.punchingTime
      })
    });
  }

  setAvailableEmployeesList() {
    this.commonService.fetchAvailableStaffInOffice().subscribe((response) => {
      this.staffList = response;
    });
  }

}
