package com.cognizant.ormlearn.payroll;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "department_id") private Department department;
    @ManyToMany @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();
    protected Employee() { }
    public Employee(String name, Department department) { this.name = name; this.department = department; }
    public Long getId() { return id; } public String getName() { return name; } public Department getDepartment() { return department; }
    public Set<Skill> getSkills() { return skills; }
    public void addSkill(Skill skill) { skills.add(skill); }
}
