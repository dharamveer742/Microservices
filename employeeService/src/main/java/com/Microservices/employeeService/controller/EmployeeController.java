package com.Microservices.employeeService.controller;

import com.Microservices.employeeService.entity.Employee;
import com.Microservices.employeeService.model.EmployeeResponse;
import com.Microservices.employeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse>  getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

}
