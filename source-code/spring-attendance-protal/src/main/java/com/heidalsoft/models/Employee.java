package com.heidelsoft.models;

import com.heidelsoft.utils.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Set<AttendanceStatus> attendanceStatus;

}
