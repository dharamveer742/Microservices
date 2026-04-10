package com.Microservices.AddressService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id ;
    @Column(name="line1")
    String line1;
    @Column(name="line2")
    String line2;
    @Column(name="state")
    String state;
    @Column(name="zip")
    String zip;
    @Column(name="employeeid")
    int employeeid;
}
