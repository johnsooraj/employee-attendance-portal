package com.heidalsoft.services.impl;

import com.heidalsoft.models.Employee;
import com.heidalsoft.repository.AttendanceLogRepository;
import com.heidalsoft.repository.EmployeeRepository;
import com.heidalsoft.services.EmployeeService;
import com.heidalsoft.utils.LogginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    AttendanceLogRepository logRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Optional<Employee> addNewEmployee(Employee employee) {
        employee.setCreateDate(LocalDateTime.now());
        employeeRepository.save(employee);
        return Optional.of(employee.getId() == null ? null : employee);
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) {
        return Optional.empty();
    }

    @Override
    public Optional<LogginResponse> removeEmployee(String id) {
        try {
            employeeRepository.deleteById(id);
            return Optional.of(new LogginResponse(true, "Successfully Removed User!"));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(new LogginResponse(false, "Failed To Remove User!"));
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(String employee) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> getAllEmployees(int pageNo, int limit) {
        Pageable pageable = PageRequest.of(pageNo, limit, Sort.by("createDate").descending());
        List<Employee> employees = employeeRepository.findAll(pageable).stream().collect(Collectors.toList());
        return Optional.of(employees);
    }
}
