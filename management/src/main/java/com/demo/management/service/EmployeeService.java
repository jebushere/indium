package com.demo.management.service;

import com.demo.management.entity.Employee;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    void deleteEmployeeById(@NotNull(message = "Employee Id cannot be null") Long empId);

    Employee update(Employee employee);

    Employee saveOrUpdate(Employee employee);
}
