package com.cognizant.employeemanagement.employee;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<EmployeeSummary> findByDepartmentName(String department);
    @Query("select e from Employee e join fetch e.department where e.email=:email")
    Optional<Employee> findDetailedByEmail(@Param("email") String email);
}
