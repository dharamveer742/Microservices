package com.Microservices.AddressService.controller;

import com.Microservices.AddressService.model.AddressResponse;
import com.Microservices.AddressService.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/address/{empid}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable("empid") int empid){
        return addressService.getAddress(empid);
    }
}
