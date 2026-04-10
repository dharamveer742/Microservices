package com.Microservices.employeeService.service;

import com.Microservices.employeeService.entity.Employee;
import com.Microservices.employeeService.model.AddressResponse;
import com.Microservices.employeeService.model.EmployeeResponse;
import com.Microservices.employeeService.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;


    private RestTemplate restTemplate;

    public EmployeeService(@Value("${addressService.base.url}") String addressBaseURL, RestTemplateBuilder  builder) {
        this.restTemplate=builder.rootUri(addressBaseURL).build();
    }



    public ResponseEntity<EmployeeResponse> getEmployee(int id)
    {
        Employee emp =  employeeRepository.findById(id).get();
        EmployeeResponse employeeResponse = modelMapper.map(emp,EmployeeResponse.class);
        ResponseEntity<AddressResponse> response = restTemplate.exchange("/address/{id}", HttpMethod.GET,null,AddressResponse.class,id);
        AddressResponse addressResponse = response.getBody();
        employeeResponse.setAddressResponse(addressResponse);

        /*
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(id);
        employeeResponse.setName(emp.getName());
        employeeResponse.setEmail(emp.getEmail());
        employeeResponse.setBloodgroup(emp.getBloodgroup());

         */
        return ResponseEntity.ok(employeeResponse);
    }
}
