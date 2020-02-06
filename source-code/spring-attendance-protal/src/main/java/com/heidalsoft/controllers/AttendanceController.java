package com.heidalsoft.controllers;

import com.heidalsoft.services.AttendanceService;
import com.heidalsoft.utils.AttendanceException;
import com.heidalsoft.utils.AttendanceLogRequest;
import com.heidalsoft.utils.AttendanceStatus;
import com.heidalsoft.utils.LogginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    /**
     * This request will call from the device outside the door (a access card/bio-metric)
     *
     * @param id
     * @return
     */
    @GetMapping("/employee/log-in")
    public ResponseEntity<LogginResponse> punchIn(@PathParam("id") String id) {
        LogginResponse logginResponse = new LogginResponse();
        try {
            logginResponse.setLoggingStatus(attendanceService.employeePunchEvent(id, AttendanceStatus.PUNCHING_IN));
            logginResponse.setMessage("Welcome..");
        } catch (AttendanceException e) {
            e.printStackTrace();
            logginResponse.setLoggingStatus(false);
            logginResponse.setMessage("Invalid user!");
        }
        return new ResponseEntity<LogginResponse>(logginResponse, HttpStatus.OK);
    }

    /**
     * This request will call from the device inside the door (a access card/bio-metric)
     *
     * @param id
     * @return
     */
    @GetMapping("/employee/log-out")
    public ResponseEntity<LogginResponse> punchOut(@PathParam("id") String id) {
        LogginResponse logginResponse = new LogginResponse();
        ;
        try {
            logginResponse.setLoggingStatus(attendanceService.employeePunchEvent(id, AttendanceStatus.PUNCHING_OUT));
            logginResponse.setMessage("Thank you..");
        } catch (AttendanceException e) {
            e.printStackTrace();
            logginResponse.setLoggingStatus(false);
            logginResponse.setMessage("Invalid user!");
        }
        return new ResponseEntity<LogginResponse>(logginResponse, HttpStatus.OK);
    }

    @PostMapping("/attendance/log")
    public Object fetchEmployeeAttendanceDetailsByUserId(@RequestBody AttendanceLogRequest attendanceLogRequest) {
        try {
            return attendanceService.attendanceDetailsByEmployeeIdAndDate(attendanceLogRequest);
        } catch (AttendanceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/available-staffs")
    public Object fetchAvailableStaffs() {
        return attendanceService.fetchAvailableEmployees();
    }


}
