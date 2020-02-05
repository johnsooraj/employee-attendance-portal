package com.heidalsoft.utils;

import com.heidalsoft.models.AttendanceLog;
import lombok.Data;

import java.util.List;

@Data
public class AttendanceLogModel {

    private Double totlaWorkingHours;
    private List<AttendanceLog> attendanceLogList;
    private AttendanceLog firstLogin;
    private AttendanceLog lastLogin;
}
