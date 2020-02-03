package com.heidalsoft.services;

import com.heidalsoft.utils.AttendanceException;
import com.heidalsoft.utils.AttendanceLogRequest;
import com.heidalsoft.utils.AttendanceStatus;

public interface AttendanceService {

    public boolean employeePunchEvent(String empId, AttendanceStatus type) throws AttendanceException;

    public Object attendanceDeatilsByEmployeeIdAndDate(AttendanceLogRequest logRequest) throws AttendanceException;
}
