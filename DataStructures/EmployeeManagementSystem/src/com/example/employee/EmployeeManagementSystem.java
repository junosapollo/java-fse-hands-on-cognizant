package com.example.employee;

class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Employee [ID=" + employeeId + ", Name=" + name + ", Position=" + position + ", Salary=$" + salary + "]";
    }
}

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size; // Current number of employees
    private int capacity;

    public EmployeeManagementSystem(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    // Add employee: O(1) if space available
    public void addEmployee(Employee emp) {
        if (size < capacity) {
            employees[size++] = emp;
            System.out.println("Added Employee: " + emp.getName());
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    // Search employee by ID: O(n)
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse employees: O(n)
    public void traverseEmployees() {
        System.out.println("\nAll Employees:");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete employee by ID: O(n) to find, O(n) to shift elements
    public void deleteEmployee(String employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.out.println("\nDeleting Employee: " + employees[index].getName());
            // Shift elements to the left
            for (int i = index; i < size - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[size - 1] = null; // clear the last element
            size--;
        } else {
            System.out.println("\nEmployee ID " + employeeId + " not found for deletion.");
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        ems.addEmployee(new Employee("E101", "Alice", "Manager", 80000));
        ems.addEmployee(new Employee("E102", "Bob", "Developer", 60000));
        ems.addEmployee(new Employee("E103", "Charlie", "Designer", 55000));

        ems.traverseEmployees();

        System.out.println("\nSearching for E102:");
        Employee found = ems.searchEmployee("E102");
        System.out.println(found != null ? "Found: " + found : "Not Found");

        ems.deleteEmployee("E102");
        
        ems.traverseEmployees();
        
        // Array representation is simple and offers O(1) random access by index.
        // However, searching by ID takes O(n), and deleting takes O(n) because of the need to shift elements.
        // A dynamically sized array like ArrayList or a HashMap would be better for scaling.
    }
}
