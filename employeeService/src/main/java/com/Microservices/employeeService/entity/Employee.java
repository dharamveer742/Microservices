package com.Microservices.employeeService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Column(name="bloodgroup")
    String bloodgroup;
}
