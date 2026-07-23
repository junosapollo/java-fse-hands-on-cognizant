package com.cognizant.employeemanagement.employee;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }
    @GetMapping public Page<Employee> search(@RequestParam(defaultValue="") String name, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size) { return service.search(name, page, size); }
    @GetMapping("/{id}") public Employee get(@PathVariable long id) { return service.get(id); }
    @GetMapping("/summary") public List<EmployeeSummary> summary(@RequestParam String department) { return service.summaries(department); }
    @PostMapping public Employee save(@Valid @RequestBody Employee employee) { return service.save(employee); }
    @DeleteMapping("/{id}") public void delete(@PathVariable long id) { service.delete(id); }
}
