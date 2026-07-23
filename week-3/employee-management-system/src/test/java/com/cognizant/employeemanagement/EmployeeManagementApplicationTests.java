package com.cognizant.employeemanagement;

import static org.assertj.core.api.Assertions.assertThat;
import com.cognizant.employeemanagement.department.*;
import com.cognizant.employeemanagement.employee.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeManagementApplicationTests {
    @Autowired DepartmentRepository departments;
    @Autowired EmployeeRepository employees;
    @Test void storesAndSearchesEmployees() {
        Department department = departments.save(new Department("Engineering"));
        employees.save(new Employee("Ada Lovelace", "ada@example.test", 100, department));
        assertThat(employees.findByNameContainingIgnoreCase("ada", org.springframework.data.domain.PageRequest.of(0, 10))).hasSize(1);
    }
}
