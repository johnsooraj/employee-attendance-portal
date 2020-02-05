import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';
import { CustomHttpResponse } from '../models/httpResponse';
import { url } from 'inspector';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  FETCH_ALL_EMPLOYEES = '/portal/employees';
  ADD_NEW_EMPLOYEE = '/portal/employee';
  FETCH_ATTENDANCE_LOG_BY_EMP_ID = '/portal/attendance/log';
  CHECK_IN_EMPLOYEE = '/portal/employees';
  CHECK_OUT_EMPLOYEE = '/portal/employees';
  DELETE_EMPLOYEE = '/portal/employee/remove';

  // home page employee list
  employeeList = new Array<Employee>();

  constructor(
    private http: HttpClient
  ) { }

  getDummyData(): Observable<any> {
    return this.http.get('https://jsonplaceholder.typicode.com/posts?_start=0&_limit=5');
  }

  fetchAllEmployees(): void {
    let params = new HttpParams();
    params = params.append('page', '0');
    params = params.append('limit', '100');
    this.http.get<Employee[]>(this.FETCH_ALL_EMPLOYEES, { params }).subscribe((empAll) => {
      this.employeeList = empAll;
    });
  }

  saveEmployee(data: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.ADD_NEW_EMPLOYEE, data);
  }

  removeEmployee(id: String, password: string): Observable<CustomHttpResponse> {
    let body = {
      "id": id,
      "password": password
    }
    return this.http.post<CustomHttpResponse>(this.DELETE_EMPLOYEE, body);
  }

  employeeCheckInOut(value: string, id: string) {
    let URL = '/portal/employee/'
    if (value == 'login') {
      URL = URL + 'log-in';
    } else {
      URL = URL + 'log-out';
    }
    let params = new HttpParams();
    params = params.append('id', id);
    this.http.get<CustomHttpResponse>(URL, {params}).subscribe((response) => {
      if(response.loggingStatus){
        window.alert(response.message);
      }
    });
  }
}
