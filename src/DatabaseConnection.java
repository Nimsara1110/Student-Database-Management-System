import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Update these values with your MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USERNAME = "root"; // default MySQL username
    private static final String PASSWORD = "Nim@2002"; // change to your MySQL password
    
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create and return the connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}
