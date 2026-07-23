package com.cognizant.employeemanagement.department;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository repository;
    public DepartmentController(DepartmentRepository repository) { this.repository = repository; }
    @GetMapping public List<Department> all() { return repository.findAll(); }
    @PostMapping public Department save(@RequestBody Department department) { return repository.save(department); }
    @GetMapping("/search") public List<Department> search(@RequestParam String name) { return repository.findByNameContainingIgnoreCase(name); }
}
