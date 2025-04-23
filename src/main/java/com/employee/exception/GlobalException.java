package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmployeeNotFount.class)
    public ResponseEntity<String> handleEmployeeNotFount(EmployeeNotFount employeeNotFount) {
        return new ResponseEntity<>(employeeNotFount.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception eg) {
        return new ResponseEntity<>("something went wrong" + eg.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
