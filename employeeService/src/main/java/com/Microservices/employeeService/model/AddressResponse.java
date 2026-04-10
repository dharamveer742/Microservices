package com.Microservices.employeeService.model;

import lombok.Data;

@Data
public class AddressResponse {
    int id ;
    String line1;
    String line2;
    String state;
    String zip;
}
