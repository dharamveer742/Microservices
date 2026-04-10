package com.Microservices.AddressService.service;

import com.Microservices.AddressService.entity.Address;
import com.Microservices.AddressService.model.AddressResponse;
import com.Microservices.AddressService.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<AddressResponse> getAddress(int empid){
        Address address = addressRepository.findById(empid).get();
        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
        return ResponseEntity.ok(addressResponse);
    }
}
