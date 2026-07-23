package com.cognizant.ormlearn.payroll;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String name;
    @OneToMany(mappedBy = "department") private List<Employee> employees = new ArrayList<>();
    protected Department() { }
    public Department(String name) { this.name = name; }
    public Long getId() { return id; } public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }
}
