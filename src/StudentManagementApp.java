import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private Scanner scanner;
    private StudentDAO studentDAO;
    
    public StudentManagementApp() {
        scanner = new Scanner(System.in);
        studentDAO = new StudentDAO();
    }
    
    public void start() {
        System.out.println("=== Student Management System ===");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    viewStudentById();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    searchStudents();
                    break;
                case 7:
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. View Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Search Students");
        System.out.println("7. Exit");
    }
    
    private void addStudent() {
        System.out.println("\n=== Add New Student ===");
        
        String name = getStringInput("Enter name: ");
        String email = getStringInput("Enter email: ");
        int age = getIntInput("Enter age: ");
        String course = getStringInput("Enter course: ");
        LocalDate enrollmentDate = LocalDate.now(); // Current date
        
        Student student = new Student(name, email, age, course, enrollmentDate);
        
        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }
    
    private void viewAllStudents() {
        System.out.println("\n=== All Students ===");
        List<Student> students = studentDAO.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    
    private void viewStudentById() {
        int id = getIntInput("Enter student ID: ");
        Student student = studentDAO.getStudentById(id);
        
        if (student != null) {
            System.out.println("\n=== Student Details ===");
            System.out.println(student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
    
    private void updateStudent() {
        int id = getIntInput("Enter student ID to update: ");
        Student student = studentDAO.getStudentById(id);
        
        if (student == null) {
            System.out.println("Student not found with ID: " + id);
            return;
        }
        
        System.out.println("Current details: " + student);
        System.out.println("\nEnter new details (press enter to keep current value):");
        
        String name = getStringInput("Enter name [" + student.getName() + "]: ");
        if (!name.isEmpty()) student.setName(name);
        
        String email = getStringInput("Enter email [" + student.getEmail() + "]: ");
        if (!email.isEmpty()) student.setEmail(email);
        
        String ageInput = getStringInput("Enter age [" + student.getAge() + "]: ");
        if (!ageInput.isEmpty()) student.setAge(Integer.parseInt(ageInput));
        
        String course = getStringInput("Enter course [" + student.getCourse() + "]: ");
        if (!course.isEmpty()) student.setCourse(course);
        
        if (studentDAO.updateStudent(student)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }
    
    private void deleteStudent() {
        int id = getIntInput("Enter student ID to delete: ");
        
        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();
        
        if (confirmation.equals("yes")) {
            if (studentDAO.deleteStudent(id)) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student or student not found.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private void searchStudents() {
        String name = getStringInput("Enter name to search: ");
        List<Student> students = studentDAO.searchStudentsByName(name);
        
        if (students.isEmpty()) {
            System.out.println("No students found with name containing: " + name);
        } else {
            System.out.println("\n=== Search Results ===");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    public static void main(String[] args) {
        StudentManagementApp app = new StudentManagementApp();
        app.start();
    }
}
