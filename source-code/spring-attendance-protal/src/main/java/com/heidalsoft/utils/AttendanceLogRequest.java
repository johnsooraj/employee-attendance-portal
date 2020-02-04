package com.heidalsoft.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceLogRequest {

    private Integer page;
    private Integer limit;
    private String employeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
