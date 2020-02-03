import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  FETCH_ALL_EMPLOYEES = '/portal/employees';
  ADD_NEW_EMPLOYEE = '/portal/employee';
  FETCH_ATTENDANCE_LOG_BY_EMP_ID = '/portal/attendance/log';
  CHECK_IN_EMPLOYEE = '/portal/employees';
  CHECK_OUT_EMPLOYEE = '/portal/employees';

  constructor(
    private http: HttpClient
  ) { }

  getDummyData(): Observable<any> {
    return this.http.get('https://jsonplaceholder.typicode.com/posts?_start=0&_limit=5');
  }

  fetchAllEmployees(page: number, _limit: number): Observable<Employee[]> {
    return this.http.get<Employee[]>('');
  }
}
