package com.heidelsoft.models;

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
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private Employee employee;
}
