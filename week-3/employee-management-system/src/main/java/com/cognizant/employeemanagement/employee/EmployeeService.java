package com.cognizant.employeemanagement.employee;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }
    public Employee save(Employee employee) { return repository.save(employee); }
    public Page<Employee> search(String name, int page, int size) { return repository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size, Sort.by("name"))); }
    public Employee get(long id) { return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee " + id + " not found")); }
    public void delete(long id) { repository.deleteById(id); }
    public List<EmployeeSummary> summaries(String department) { return repository.findByDepartmentName(department); }
}
