package com.cognizant.employeemanagement.department;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByNameContainingIgnoreCase(String name);
}
