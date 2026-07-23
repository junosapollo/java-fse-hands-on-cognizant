package com.cognizant.ormlearn.payroll;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayrollService {
    private final EmployeeRepository employees;
    public PayrollService(EmployeeRepository employees) { this.employees = employees; }
    @Transactional(readOnly = true) public List<Employee> employeesIn(String department) { return employees.findByDepartmentName(department); }
    @Transactional(readOnly = true) public List<Employee> fetchGraph(String name) { return employees.fetchGraph(name); }
}
