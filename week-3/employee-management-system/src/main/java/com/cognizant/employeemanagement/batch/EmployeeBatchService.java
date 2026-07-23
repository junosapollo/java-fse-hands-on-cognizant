package com.cognizant.employeemanagement.batch;

import com.cognizant.employeemanagement.employee.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeBatchService {
    private final EntityManager entityManager;
    public EmployeeBatchService(EntityManager entityManager) { this.entityManager = entityManager; }
    @Transactional public void saveBatch(Iterable<Employee> employees) { employees.forEach(entityManager::persist); entityManager.flush(); entityManager.clear(); }
}
