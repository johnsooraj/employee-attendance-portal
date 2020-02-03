package com.heidalsoft.repository;

import com.heidalsoft.models.AttendanceLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceLogRepository extends MongoRepository<AttendanceLog, String> {
}
