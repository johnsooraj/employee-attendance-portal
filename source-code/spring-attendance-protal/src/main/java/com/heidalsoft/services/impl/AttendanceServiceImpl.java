package com.heidalsoft.services.impl;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogCustomRepo;
import com.heidalsoft.repository.AttendanceLogRepository;
import com.heidalsoft.repository.EmployeeRepository;
import com.heidalsoft.services.AttendanceService;
import com.heidalsoft.services.EmployeeService;
import com.heidalsoft.utils.AttendanceException;
import com.heidalsoft.utils.AttendanceLogModel;
import com.heidalsoft.utils.AttendanceLogRequest;
import com.heidalsoft.utils.AttendanceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public Object attendanceDetailsByEmployeeIdAndDate(AttendanceLogRequest logRequest) throws AttendanceException {
        Employee employee = employeeRepository.findById(logRequest.getEmployeeId()).orElseThrow(AttendanceException::new);
        List<AttendanceLog> attendanceLogs = null;
        AttendanceLogModel logModel = new AttendanceLogModel();
        Duration duration = Duration.between(logRequest.getStartDate(), logRequest.getEndDate());
        LocalDate today = LocalDate.now();
        LocalDateTime startTime = today.atStartOfDay();
        LocalDateTime endTime = today.atTime(LocalTime.MAX);
        attendanceLogs = attendanceLogCustomRepo.fetchAttendanceLogsByDateRange(employee, startTime, endTime, 0, 100);
        if (duration.toDays() < 1) {
            log.info("fetch attendance log for day {}", logRequest.getStartDate());
        } else if (duration.toDays() <= 7) {
            log.info("fetch attendance log for week start : {}, end : {}", logRequest.getStartDate(), logRequest.getEndDate());
        } else if (duration.toDays() <= 28) {
            log.info("fetch attendance log for month start : {}, end : {}", logRequest.getStartDate(), logRequest.getEndDate());
        }
        if (attendanceLogs != null && attendanceLogs.size() > 0) {
            logModel.setAttendanceLogList(attendanceLogs);
            logModel.setFirstLogin(attendanceLogs.isEmpty() ? null : attendanceLogs.get(0));
            logModel.setLastLogin(attendanceLogs.get(attendanceLogs.isEmpty() ? null : (attendanceLogs.size() - 1)));
        }
        logModel.setTotlaWorkingHours(calculateWorkHours(attendanceLogs));
        return logModel;
    }

    private Double calculateWorkHours(List<AttendanceLog> attendanceLogs) {
        long minutes = 0l;
        if (attendanceLogs != null && !attendanceLogs.isEmpty()) {
            for (int i = 0; i < attendanceLogs.size(); i++) {
                AttendanceLog login = attendanceLogs.get(i);
                LocalDateTime element1 = null;
                LocalDateTime element2 = null;
                if (login.getPunchingType() == AttendanceStatus.PUNCHING_IN) {
                    element1 = login.getPunchingTime();
                    int next = i + 1;
                    try {
                        if (attendanceLogs.get(next).getPunchingType() == AttendanceStatus.PUNCHING_OUT) {
                            element2 = attendanceLogs.get(next).getPunchingTime();
                            i++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        log.info("log empty and next index is {}", next);
                    }
                }
                if (element1 != null && element2 != null) {
                    Duration duration = Duration.between(element1, element2);
                    log.info("duration in hour : {}", duration.toMinutes());
                    minutes = minutes + duration.toMinutes();
                }
            }
        }
        long hours = minutes / 60;
        long minutes2 = minutes % 60;
        return new Double(hours + "." + minutes2);
    }

    @Override
    public List<AttendanceLog> fetchAvailableEmployees() {
        LocalDate today = LocalDate.now();
        LocalDateTime startTime = today.atStartOfDay();
        LocalDateTime endTime = today.atTime(LocalTime.MAX);
        return attendanceLogCustomRepo.fetchAvailableEmployees(startTime, endTime);
    }

}
