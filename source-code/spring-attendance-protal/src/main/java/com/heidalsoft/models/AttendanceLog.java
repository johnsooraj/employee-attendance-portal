package com.heidalsoft.models;

import com.heidalsoft.utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceLog log = (AttendanceLog) o;
        return punchingTime.equals(log.punchingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punchingTime);
    }
}
