import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Employee } from 'src/app/models/employee';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.scss']
})
export class AttendanceComponent implements OnInit {

  dropdownValue = new Employee();

  constructor(
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.fetchAllEmployees();
  }

  employeeSelection(emp: Employee) {
    this.dropdownValue = emp;
  }

  employeeLogin(value: string) {
    this.commonService.employeeCheckInOut(value, this.dropdownValue.id);
  }

}
