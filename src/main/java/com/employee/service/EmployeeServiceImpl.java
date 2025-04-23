package com.employee.service;

import com.employee.conversion.Conversion;
import com.employee.dto.EmployeeDto;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.security.PasswordService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JavaMailSender javaMailSender;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee addEmployee(Employee employee) {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);
        employee.setEmpId(randomNumber);
        String hashedPassword = passwordService.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        Employee savedEmployee = employeeRepository.save(employee);
        String subject = "Welcome to the company";
        String email = employee.getEmail();
        String body = "Dear " + employee.getEmpName() + ",\n\nYour account has been created successfully." + "\nYour Employee ID : " + employee.getEmpId() + "," + "\nYour User Name : " + employee.getUserName() + "" + "\nPlease save it.";
        employee.getUserName();
        sendEmail(email, subject, body);
        return savedEmployee;
    }


    public Employee getEmployee(int empId) {
        Optional<Employee> byId = employeeRepository.findById(empId);
        if (byId.isEmpty()) {
            System.out.println("Id not registered");
        }
        return byId.get();
    }

    public EmployeeDto updateEmployee(Employee employee) {
        EmployeeDto employeeDto = Conversion.convertToEmployeeDto(employee);
        if (employeeDto.getEmpId() != 0 && employeeDto.getEmpId() != null) {
            Employee save = employeeRepository.save(employee);
        }
        return employeeDto;
    }

    private void sendEmail(String toEmail, String subject, String body) {
        logger.info("value of employee:{}", toEmail);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("santoshlimbale76@gmail.com");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}