package com.heidalsoft.services.impl;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogCustomRepo;
import com.heidalsoft.repository.AttendanceLogRepository;
import com.heidalsoft.repository.EmployeeRepository;
import com.heidalsoft.services.AttendanceService;
import com.heidalsoft.services.EmployeeService;
import com.heidalsoft.utils.AttendanceException;
import com.heidalsoft.utils.AttendanceLogRequest;
import com.heidalsoft.utils.AttendanceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceLogRepository attendanceLogRepository;

    @Autowired
    AttendanceLogCustomRepo attendanceLogCustomRepo;

    @Override
    public boolean employeePunchEvent(String empId, AttendanceStatus type) throws AttendanceException {
        Employee employee = employeeRepository.findById(empId).orElseThrow(AttendanceException::new);
        AttendanceLog attendanceLog = new AttendanceLog(employee, type);
        attendanceLogRepository.save(attendanceLog);
        return attendanceLog.getId() != null ? true : false;
    }

    @Override
    public Object attendanceDeatilsByEmployeeIdAndDate(AttendanceLogRequest logRequest) throws AttendanceException {
        Employee employee = employeeRepository.findById(logRequest.getEmployeeId()).orElseThrow(AttendanceException::new);
        List<AttendanceLog> attendanceLogs = null;
        Duration duration = Duration.between(logRequest.getStartDate(), logRequest.getEndDate());
        log.info(duration.toDays() + "");
        attendanceLogs = attendanceLogCustomRepo.fetchAttendanceLogsByDateRange(employee, logRequest.getStartDate(), logRequest.getEndDate(), 0, 10);
        if (duration.toDays() < 1) {
            log.info("fetch attendance log for day {}", logRequest.getStartDate());
        } else if (duration.toDays() <= 7) {
            log.info("fetch attendance log for week start : {}, end : {}", logRequest.getStartDate(), logRequest.getEndDate());
        } else if (duration.toDays() <= 28) {
            log.info("fetch attendance log for month start : {}, end : {}", logRequest.getStartDate(), logRequest.getEndDate());
        }
        return attendanceLogs;
    }

}
