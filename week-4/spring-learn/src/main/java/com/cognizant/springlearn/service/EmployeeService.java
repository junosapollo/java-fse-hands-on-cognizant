package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final AtomicLong sequence = new AtomicLong(2);
    private final List<Employee> employees = new CopyOnWriteArrayList<>(List.of(
        new Employee(1, "Ada Lovelace", "ada@example.test", new Department("ENG", "Engineering"), List.of(new Skill("JAVA", "Java"))),
        new Employee(2, "Grace Hopper", "grace@example.test", new Department("ENG", "Engineering"), List.of(new Skill("SQL", "SQL")))
    ));
    public List<Employee> all() { return List.copyOf(employees); }
    public Employee get(long id) { return employees.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new NoSuchElementException("employee " + id)); }
    public Employee create(Employee employee) { Employee saved = new Employee(sequence.incrementAndGet(), employee.getName(), employee.getEmail(), employee.getDepartment(), employee.getSkills()); employees.add(saved); return saved; }
    public Employee update(long id, Employee incoming) { Employee old=get(id); old.setName(incoming.getName()); old.setEmail(incoming.getEmail()); return old; }
    public void delete(long id) { employees.removeIf(e -> e.getId() == id); }
}
