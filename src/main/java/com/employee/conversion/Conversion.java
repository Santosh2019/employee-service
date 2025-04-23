package com.employee.conversion;

import com.employee.dto.EmployeeDto;
import com.employee.model.Employee;

public class Conversion {

    public static Employee convertToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmpId(employeeDto.getEmpId());
        employee.setSalary(employeeDto.getSalary());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setEmail(employeeDto.getEmail());
        return employee;
    }

    public static EmployeeDto convertToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setEmpName(employee.getEmpName());
        employeeDto.setEmpId(employee.getEmpId());
        employeeDto.setEmail(employee.getEmail());
        return employeeDto;
    }


}
