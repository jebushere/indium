package com.demo.management.service.impl;

import com.demo.management.entity.Employee;
import com.demo.management.repository.EmployeeRepository;
import com.demo.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(@NotNull(message = "Employee Id cannot be null") Long empId) {
        Optional<Employee> oEmployee = employeeRepository.findById(empId);
        oEmployee.orElseThrow(ValidationException::new);
        employeeRepository.deleteById(empId);
    }

    @Override
    public Employee update(Employee employee) {
        Optional<Employee> oEmployee = employeeRepository.findById(employee.getId());
        oEmployee.orElseThrow(ValidationException::new);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }
}
