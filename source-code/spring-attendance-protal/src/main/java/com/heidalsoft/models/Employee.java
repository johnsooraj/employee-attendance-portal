package com.heidalsoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heidalsoft.utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String surName;
    private String mobile;
    private String email;
    private String designation;

    @CreatedDate
    private LocalDateTime createDate;

    @JsonIgnore
    private Set<AttendanceStatus> attendanceStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) &&
                mobile.equals(employee.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobile);
    }
}
