package com.Microservices.employeeService.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class EmployeeResponse {

    int id;

    String name;

    String email;

    String bloodgroup;

    AddressResponse addressResponse;
}
