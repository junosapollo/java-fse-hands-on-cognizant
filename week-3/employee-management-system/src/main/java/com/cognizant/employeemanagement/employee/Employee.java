package com.cognizant.employeemanagement.employee;

import com.cognizant.employeemanagement.department.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @PositiveOrZero private int salary;
    @ManyToOne(optional = false) private Department department;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    private Instant modifiedAt;
    protected Employee() { }
    public Employee(String name, String email, int salary, Department department) { this.name=name; this.email=email; this.salary=salary; this.department=department; }
    @PrePersist void created() { createdAt = Instant.now(); modifiedAt = createdAt; }
    @PreUpdate void modified() { modifiedAt = Instant.now(); }
    public Long getId() { return id; } public String getName() { return name; } public String getEmail() { return email; }
    public int getSalary() { return salary; } public Department getDepartment() { return department; }
    public Instant getCreatedAt() { return createdAt; } public Instant getModifiedAt() { return modifiedAt; }
    public void setDepartment(Department department) { this.department = department; }
}
