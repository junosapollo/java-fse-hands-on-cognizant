package com.example.task;

class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() { return taskId; }

    @Override
    public String toString() {
        return "Task [ID=" + taskId + ", Name=" + taskName + ", Status=" + status + "]";
    }
}

// node class for singly linked list
class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManagementSystem {
    private Node head;

    // add task at the end without tail pointer if we keep track of tail
    // using simple traversal for demonstration
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Added Task: " + task.getTaskId());
    }

    // search task by id
    public Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // traverse tasks
    public void traverseTasks() {
        System.out.println("\nAll Tasks:");
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // delete task by id
    public void deleteTask(String taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        // if the task to be deleted is the head node
        if (head.task.getTaskId().equals(taskId)) {
            System.out.println("\nDeleting Task: " + head.task.getTaskId());
            head = head.next;
            return;
        }

        Node current = head;
        Node prev = null;
        
        while (current != null && !current.task.getTaskId().equals(taskId)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("\nTask ID " + taskId + " not found for deletion.");
            return;
        }

        System.out.println("\nDeleting Task: " + current.task.getTaskId());
        prev.next = current.next;
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.addTask(new Task("T1", "Design Database", "In Progress"));
        tms.addTask(new Task("T2", "Develop API", "Pending"));
        tms.addTask(new Task("T3", "Write Tests", "Pending"));

        tms.traverseTasks();

        System.out.println("\nSearching for T2:");
        Task found = tms.searchTask("T2");
        System.out.println(found != null ? "Found: " + found : "Not Found");

        tms.deleteTask("T2");
        
        tms.traverseTasks();
        
        // linked lists advantage dynamic size insertionsdeletions once the node is found no shifting elements
        // disadvantage search time since random access by index is not possible
    }
}
