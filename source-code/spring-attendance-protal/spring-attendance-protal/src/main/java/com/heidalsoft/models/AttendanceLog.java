package com.heidalsoft.models;

import com.heidalsoft.utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AttendanceLog {

    @Id
    private String id;
    private LocalDateTime punchingTime;
    private AttendanceStatus punchingType;
    private Employee employee;

    public AttendanceLog(Employee employee, AttendanceStatus type) {
        this.employee = employee;
        this.punchingType = type;
        this.punchingTime = LocalDateTime.now();
    }
}
