package com.heidalsoft.controllers;

import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogRepository;
import com.heidalsoft.repository.EmployeeRepository;
import com.heidalsoft.services.AttendanceService;
import com.heidalsoft.services.EmployeeService;
import com.heidalsoft.utils.LogginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Value("${admin.password}")
    private String TEMP_PASSWORD;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        if (employee.getDesignation() == null) {
            employee.setDesignation("Trainee");
        }
        return new ResponseEntity<Employee>(employeeService.addNewEmployee(employee).get(), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathParam("page") Integer page, @PathParam("limit") Integer limit) {
        List<Employee> employees = employeeService.getAllEmployees(page, limit).get();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/employee/remove")
    public ResponseEntity<LogginResponse> removeEmploee(@RequestBody Map<String, String> body) {
        if (TEMP_PASSWORD.equals(body.get("password"))) {
            LogginResponse logginResponse = employeeService.removeEmployee(body.get("id")).get();
            return new ResponseEntity<>(logginResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LogginResponse(false, "Invalid Password!"), HttpStatus.BAD_REQUEST);
        }
    }

}
