import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    
    // Create a new student
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, age, course, enrollment_date) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getCourse());
            pstmt.setDate(5, Date.valueOf(student.getEnrollmentDate()));
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
                
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        
        return students;
    }
    
    // Get student by ID
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
        
        return student;
    }
    
    // Update student
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, age = ?, course = ?, enrollment_date = ? WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getCourse());
            pstmt.setDate(5, Date.valueOf(student.getEnrollmentDate()));
            pstmt.setInt(6, student.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
    
    // Delete student
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
    
    // Search students by name
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
                
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching students: " + e.getMessage());
        }
        
        return students;
    }
}
