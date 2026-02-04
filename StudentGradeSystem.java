import java.sql.*;
import java.util.Scanner;

public class StudentGradeSystem {
    // Database connection details
    private static final String DB_URL = "jdbc:sqlite:student_grades.db";
    private static Connection connection = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Initialize database
            initializeDatabase();
            
            // Main menu loop
            boolean running = true;
            while (running) {
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
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        calculateClassAverage();
                        break;
                    case 7:
                        displayStatistics();
                        break;
                    case 8:
                        System.out.println("\n✓ Thank you for using Student Grade Management System!");
                        running = false;
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please try again.");
                }
            }
            
            // Close database connection
            if (connection != null) {
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Initialize database and create table
    private static void initializeDatabase() throws SQLException {
        try {
            // Explicitly load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found!");
            System.out.println("Make sure sqlite-jdbc-3.44.1.0.jar is in the same folder.");
            throw new SQLException("SQLite JDBC Driver not found", e);
        }
        
        connection = DriverManager.getConnection(DB_URL);
        Statement stmt = connection.createStatement();
        
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_id TEXT UNIQUE NOT NULL," +
                "name TEXT NOT NULL," +
                "subject TEXT NOT NULL," +
                "marks INTEGER NOT NULL," +
                "grade TEXT NOT NULL," +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        
        stmt.execute(createTableSQL);
        System.out.println("✓ Database initialized successfully!");
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student Marks");
        System.out.println("5. Delete Student Record");
        System.out.println("6. Calculate Class Average");
        System.out.println("7. Display Statistics");
        System.out.println("8. Exit");
        System.out.println("=".repeat(50));
    }

    // Add new student
    private static void addStudent() {
        try {
            System.out.println("\n--- Add New Student ---");
            
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine().trim();
            
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Subject: ");
            String subject = scanner.nextLine().trim();
            
            int marks = getIntInput("Enter Marks (0-100): ");
            
            // Validate marks
            if (marks < 0 || marks > 100) {
                System.out.println("\n✗ Invalid marks! Must be between 0 and 100.");
                return;
            }
            
            // Calculate grade
            String grade = calculateGrade(marks);
            
            // Insert into database
            String sql = "INSERT INTO students (student_id, name, subject, marks, grade) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, subject);
            pstmt.setInt(4, marks);
            pstmt.setString(5, grade);
            
            pstmt.executeUpdate();
            
            System.out.println("\n✓ Student added successfully!");
            System.out.println("Grade: " + grade);
            
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("\n✗ Error: Student ID already exists!");
            } else {
                System.out.println("\n✗ Error adding student: " + e.getMessage());
            }
        }
    }

    // View all students
    private static void viewAllStudents() {
        try {
            String sql = "SELECT * FROM students ORDER BY student_id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("\n" + "=".repeat(90));
            System.out.println("                               ALL STUDENTS");
            System.out.println("=".repeat(90));
            System.out.printf("%-12s %-20s %-15s %-8s %-8s%n", 
                "Student ID", "Name", "Subject", "Marks", "Grade");
            System.out.println("-".repeat(90));
            
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.printf("%-12s %-20s %-15s %-8d %-8s%n",
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("subject"),
                    rs.getInt("marks"),
                    rs.getString("grade"));
            }
            
            if (!hasRecords) {
                System.out.println("No student records found.");
            }
            
            System.out.println("=".repeat(90));
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error retrieving students: " + e.getMessage());
        }
    }

    // Search student by ID
    private static void searchStudent() {
        try {
            System.out.print("\nEnter Student ID to search: ");
            String studentId = scanner.nextLine().trim();
            
            String sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("Student ID   : " + rs.getString("student_id"));
                System.out.println("Name         : " + rs.getString("name"));
                System.out.println("Subject      : " + rs.getString("subject"));
                System.out.println("Marks        : " + rs.getInt("marks"));
                System.out.println("Grade        : " + rs.getString("grade"));
                System.out.println("=".repeat(50));
            } else {
                System.out.println("\n✗ Student not found!");
            }
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error searching student: " + e.getMessage());
        }
    }

    // Update student marks
    private static void updateStudent() {
        try {
            System.out.print("\nEnter Student ID to update: ");
            String studentId = scanner.nextLine().trim();
            
            // Check if student exists
            String checkSql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, studentId);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("\n✗ Student not found!");
                return;
            }
            
            System.out.println("\nCurrent Marks: " + rs.getInt("marks"));
            int newMarks = getIntInput("Enter New Marks (0-100): ");
            
            if (newMarks < 0 || newMarks > 100) {
                System.out.println("\n✗ Invalid marks! Must be between 0 and 100.");
                return;
            }
            
            String newGrade = calculateGrade(newMarks);
            
            String updateSql = "UPDATE students SET marks = ?, grade = ? WHERE student_id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setInt(1, newMarks);
            updateStmt.setString(2, newGrade);
            updateStmt.setString(3, studentId);
            
            updateStmt.executeUpdate();
            
            System.out.println("\n✓ Student record updated successfully!");
            System.out.println("New Grade: " + newGrade);
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error updating student: " + e.getMessage());
        }
    }

    // Delete student record
    private static void deleteStudent() {
        try {
            System.out.print("\nEnter Student ID to delete: ");
            String studentId = scanner.nextLine().trim();
            
            System.out.print("Are you sure you want to delete this student? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("yes")) {
                System.out.println("\n✗ Deletion cancelled.");
                return;
            }
            
            String sql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, studentId);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("\n✓ Student record deleted successfully!");
            } else {
                System.out.println("\n✗ Student not found!");
            }
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error deleting student: " + e.getMessage());
        }
    }

    // Calculate class average
    private static void calculateClassAverage() {
        try {
            String sql = "SELECT AVG(marks) as average, COUNT(*) as total FROM students";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                int total = rs.getInt("total");
                if (total > 0) {
                    double average = rs.getDouble("average");
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("Class Average Marks: " + String.format("%.2f", average));
                    System.out.println("Total Students: " + total);
                    System.out.println("=".repeat(50));
                } else {
                    System.out.println("\n✗ No student records found!");
                }
            }
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error calculating average: " + e.getMessage());
        }
    }

    // Display statistics
    private static void displayStatistics() {
        try {
            String sql = "SELECT " +
                    "COUNT(*) as total, " +
                    "AVG(marks) as average, " +
                    "MAX(marks) as highest, " +
                    "MIN(marks) as lowest, " +
                    "SUM(CASE WHEN grade = 'A+' THEN 1 ELSE 0 END) as grade_a_plus, " +
                    "SUM(CASE WHEN grade = 'A' THEN 1 ELSE 0 END) as grade_a, " +
                    "SUM(CASE WHEN grade = 'B' THEN 1 ELSE 0 END) as grade_b, " +
                    "SUM(CASE WHEN grade = 'C' THEN 1 ELSE 0 END) as grade_c, " +
                    "SUM(CASE WHEN grade = 'F' THEN 1 ELSE 0 END) as grade_f " +
                    "FROM students";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next() && rs.getInt("total") > 0) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("              CLASS STATISTICS");
                System.out.println("=".repeat(50));
                System.out.println("Total Students    : " + rs.getInt("total"));
                System.out.println("Average Marks     : " + String.format("%.2f", rs.getDouble("average")));
                System.out.println("Highest Marks     : " + rs.getInt("highest"));
                System.out.println("Lowest Marks      : " + rs.getInt("lowest"));
                System.out.println("\nGrade Distribution:");
                System.out.println("  A+ Grade        : " + rs.getInt("grade_a_plus"));
                System.out.println("  A Grade         : " + rs.getInt("grade_a"));
                System.out.println("  B Grade         : " + rs.getInt("grade_b"));
                System.out.println("  C Grade         : " + rs.getInt("grade_c"));
                System.out.println("  F Grade         : " + rs.getInt("grade_f"));
                System.out.println("=".repeat(50));
            } else {
                System.out.println("\n✗ No student records found!");
            }
            
        } catch (SQLException e) {
            System.out.println("\n✗ Error displaying statistics: " + e.getMessage());
        }
    }

    // Calculate grade based on marks
    private static String calculateGrade(int marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else return "F";
    }

    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }
}
