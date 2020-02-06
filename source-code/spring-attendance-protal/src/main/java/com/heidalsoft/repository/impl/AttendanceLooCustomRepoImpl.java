package com.heidalsoft.repository.impl;

import com.heidalsoft.models.AttendanceLog;
import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogCustomRepo;
import com.heidalsoft.repository.EmployeeRepository;
import com.heidalsoft.utils.AttendanceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        query.with(Sort.by("punchingTime").ascending());
        query.addCriteria(Criteria.where("employee.id").is(employee.getId())
                .and("punchingTime").gte(startDate).lt(endDate));
        query.fields().exclude("employee");
        List<AttendanceLog> attendanceLogs = mongoTemplate.find(query, AttendanceLog.class);
        log.info(" data : {}", attendanceLogs);
        return attendanceLogs;
    }

    @Override
    public List<AttendanceLog> fetchAvailableEmployees(LocalDateTime startTime, LocalDateTime endTime) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("punchingTime").gte(startTime).lt(endTime)
        );
        query.with(Sort.by("punchingTime").ascending());
        List<AttendanceLog> attendanceLogs = mongoTemplate.find(query, AttendanceLog.class);
        HashMap<String, AttendanceLog> map = new HashMap<>();
        attendanceLogs.forEach(log -> {
            map.put(log.getEmployee().getId(), log);
        });
        log.info(" {}", map);
        return map.values().stream().collect(Collectors.toList());
    }
}
