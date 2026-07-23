package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service=service; }
    @GetMapping public List<Employee> all() { return service.all(); }
    @GetMapping("/{id}") public Employee get(@PathVariable long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Employee create(@Valid @RequestBody Employee employee) { return service.create(employee); }
    @PutMapping("/{id}") public Employee update(@PathVariable long id, @Valid @RequestBody Employee employee) { return service.update(id, employee); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id) { service.delete(id); }
}
