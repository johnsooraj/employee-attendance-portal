package com.heidalsoft.services;

import com.heidalsoft.models.Employee;
import com.heidalsoft.utils.LogginResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Optional<Employee> addNewEmployee(Employee employee);

    public Optional<Employee> updateEmployee(Employee employee);

    public Optional<LogginResponse> removeEmployee(String id);

    public Optional<Employee> getEmployeeById(String employee);

    public Optional<List<Employee>> getAllEmployees(int pageNo, int limit);

}
