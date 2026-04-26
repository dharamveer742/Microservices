package com.Microservices.employeeService.feignClient;

import com.Microservices.employeeService.model.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AddressService",url= "http://localhost:2526",path="/address-app/api")
public interface AddressClient {

    @GetMapping("/address/{id}")
    ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable int id);
}

//@FeignClient(name = "AddressService",path="/address-app/api")