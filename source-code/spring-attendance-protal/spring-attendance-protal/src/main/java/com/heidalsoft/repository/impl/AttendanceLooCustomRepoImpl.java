package com.heidalsoft.repository.impl;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogCustomRepo;
import com.heidalsoft.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
public class AttendanceLooCustomRepoImpl implements AttendanceLogCustomRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<AttendanceLog> fetchAttendanceLogsByDateRange(Employee employee, LocalDateTime startDate, LocalDateTime endDate, int page, int limit) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employee.id").is(employee.getId()));
        List<AttendanceLog> attendanceLogs = mongoTemplate.find(query, AttendanceLog.class);
        log.info(" data : {}", attendanceLogs);
        return attendanceLogs;
    }
}
