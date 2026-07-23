package com.cognizant.springlearn.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class Employee {
    private long id;
    @NotBlank private String name;
    @Email @NotBlank private String email;
    private Department department;
    private List<Skill> skills = List.of();
    public Employee() { }
    public Employee(long id, String name, String email, Department department, List<Skill> skills) { this.id=id; this.name=name; this.email=email; this.department=department; this.skills=skills; }
    public long getId() { return id; } public String getName() { return name; } public void setName(String n) { name=n; }
    public String getEmail() { return email; } public void setEmail(String e) { email=e; }
    public Department getDepartment() { return department; } public List<Skill> getSkills() { return skills; }
}
