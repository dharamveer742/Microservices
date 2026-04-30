package com.Microservices.employeeService.service;

import com.Microservices.employeeService.entity.Employee;
import com.Microservices.employeeService.feignClient.AddressClient;
import com.Microservices.employeeService.model.AddressResponse;
import com.Microservices.employeeService.model.EmployeeResponse;
import com.Microservices.employeeService.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AddressClient addressClient;

    @Autowired
    private RestTemplate restTemplate;

    /*
    public EmployeeService(@Value("${addressService.base.url}") String addressBaseURL, RestTemplateBuilder  builder) {
        this.restTemplate=builder.rootUri(addressBaseURL).build();
    } */

    //@Autowired
    //private WebClient webClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;



    public ResponseEntity<EmployeeResponse> getEmployee(int id)
    {
        Employee emp =  employeeRepository.findById(id).get();
        EmployeeResponse employeeResponse = modelMapper.map(emp,EmployeeResponse.class);
        ResponseEntity<AddressResponse> response =  addressClient.getAddressByEmployeeId(id);//callingAddressServiceUsingRestTemplate(id);
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

    /* uncomment imports ,bean configuration  and injection and add web Flux dependency
    private ResponseEntity<AddressResponse>  callingAddressServiceUsingWebClient(int id){
        return webClient.get().uri("/address/"+id).retrieve().toEntity(AddressResponse.class).block();
    } */

    private ResponseEntity<AddressResponse>  callingAddressServiceUsingRestTemplate(int id) {
        //List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESSSERVICE");

        ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESSSERVICE");
        String uri = serviceInstance.getUri().toString();
        String contextRoot = serviceInstance.getMetadata().get("configPath");
        System.out.println("uri:"+uri);
        System.out.println("contextRoot:"+contextRoot);
        return restTemplate.exchange(uri+contextRoot+"/address/{id}", HttpMethod.GET,null,AddressResponse.class,id);
    }
}
