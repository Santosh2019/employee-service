package com.employee.controller;

import com.employee.model.Employee;
import com.employee.security.PasswordService;
import com.employee.service.EmployeeServiceImpl;
import org.apache.logging.log4j.message.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee-service/v1/api")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private PasswordService passwordService;


    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        SimpleMessage simpleMessage = new SimpleMessage();
        Employee saveEmployee = employeeService.addEmployee(employee);
        String hashPassword = passwordService.hashPassword(saveEmployee.getPassword());
        employee.setPassword(hashPassword);
        logger.info("Employee Response data values {}:", saveEmployee);
        if (saveEmployee != null) {
            return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(saveEmployee, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("empId") Integer empId) {
        Employee employee = employeeService.getEmployee(empId);
        logger.info("Employee Response data values {}", employee);
        if (employee.getEmpId() != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
