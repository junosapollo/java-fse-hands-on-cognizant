package com.cognizant.ormlearn.payroll;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String department);
    @Query("select distinct e from Employee e left join fetch e.department left join fetch e.skills where e.name like :name")
    List<Employee> fetchGraph(@Param("name") String name);
    @Query(value = "select e.* from employee e join department d on e.department_id=d.id where d.name=:department", nativeQuery = true)
    List<Employee> findNativeByDepartment(@Param("department") String department);
}
