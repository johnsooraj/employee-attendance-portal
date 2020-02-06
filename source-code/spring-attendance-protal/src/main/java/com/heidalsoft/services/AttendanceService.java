package com.heidalsoft.services;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;
import com.heidalsoft.utils.AttendanceException;
import com.heidalsoft.utils.AttendanceLogRequest;
import com.heidalsoft.utils.AttendanceStatus;

import java.util.List;

public interface AttendanceService {

    public boolean employeePunchEvent(String empId, AttendanceStatus type) throws AttendanceException;

    public Object attendanceDetailsByEmployeeIdAndDate(AttendanceLogRequest logRequest) throws AttendanceException;

    public List<AttendanceLog> fetchAvailableEmployees();
}
