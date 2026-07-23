package com.example.mvc;

class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}

class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
        System.out.println("-------------------------");
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

public class MVCPattern {
    public static void main(String[] args) {
        // fetch student record based on his roll no from the database mock
        Student model = retrieveStudentFromDatabase();

        // create a view to write student details on console
        StudentView view = new StudentView();

        // create controller
        StudentController controller = new StudentController(model, view);

        System.out.println("Initial State:");
        controller.updateView();

        // update model data
        System.out.println("Updating Student Grade to A+...");
        controller.setStudentGrade("A+");
        controller.setStudentName("Johnathan Doe");

        System.out.println("State After Update:");
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        return new Student("S101", "John Doe", "B");
    }
}
