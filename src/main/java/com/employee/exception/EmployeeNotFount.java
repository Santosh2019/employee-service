package com.employee.exception;

public class EmployeeNotFount extends Exception {

    public EmployeeNotFount(String message) {
        super(String.format("Employee Id not found"));
    }
}
