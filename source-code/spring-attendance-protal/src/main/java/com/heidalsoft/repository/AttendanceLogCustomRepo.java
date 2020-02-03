package com.heidalsoft.repository;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceLogCustomRepo {

    List<AttendanceLog> fetchAttendanceLogsByDateRange(Employee employee, LocalDateTime startDate, LocalDateTime endDate, int page, int limit);
}
