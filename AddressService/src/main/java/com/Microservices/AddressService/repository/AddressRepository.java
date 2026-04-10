package com.Microservices.AddressService.repository;

import com.Microservices.AddressService.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query(nativeQuery = true,value="select address.id,address.line1,address.line2,address.state,address.zip from address join employee on employee.id=address.employeeid where address.employeeid=employeeId")
    Address findAddressByEmployeeid(@Param("employeeId") int employeeId);
}
