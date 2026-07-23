package com.cognizant.ormlearn.payroll;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String name;
    @ManyToMany(mappedBy = "skills") private Set<Employee> employees = new HashSet<>();
    protected Skill() { }
    public Skill(String name) { this.name = name; }
    public Long getId() { return id; } public String getName() { return name; }
}
