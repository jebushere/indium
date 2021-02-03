package com.demo.management.controller;

import com.demo.management.dto.EmployeeRequestDTO;
import com.demo.management.dto.EmployeeResponseDTO;
import com.demo.management.entity.Employee;
import com.demo.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        List<EmployeeResponseDTO> response = employees.stream().map(EmployeeResponseDTO::wrap).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok("Success");
    }

    @PutMapping(path = "/{empId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeRequestDTO.unWrap(employeeRequestDTO);
        employee.setId(empId);
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeRequestDTO.unWrap(employeeRequestDTO);
        return ResponseEntity.ok(employeeService.saveOrUpdate(employee));
    }

}
