Copy

🎓 Student Grade Management System
Show Image
Show Image
Show Image
Show Image

A Java-based console application for managing student grades with SQLite database integration. Features complete CRUD operations, automatic grade calculation, and comprehensive statistical analysis.

📸 Screenshots
==================================================
     STUDENT GRADE MANAGEMENT SYSTEM
==================================================
1. Add New Student
2. View All Students
3. Search Student by ID
4. Update Student Marks
5. Delete Student Record
6. Calculate Class Average
7. Display Statistics
8. Exit
==================================================
✨ Features
📝 Add Students - Register new students with auto-grade calculation
👁️ View Records - Display all students in formatted tables
🔍 Search - Find students by unique ID
✏️ Update - Modify student marks with automatic grade recalculation
🗑️ Delete - Remove student records with confirmation
📊 Statistics - View class average, highest/lowest marks, grade distribution
💾 Persistent Storage - All data saved in SQLite database
🎯 Grading System
Grade	Marks Range
A+	90-100
A	80-89
B	70-79
C	60-69
F	Below 60
🛠️ Tech Stack
Language: Java
Database: SQLite 3.36.0.3
API: JDBC (Java Database Connectivity)
Design Pattern: MVC-inspired architecture
📦 Installation
Prerequisites
Java JDK 8 or higher
SQLite JDBC Driver
Setup
Clone the repository
bash
   git clone https://github.com/yourusername/student-grade-system.git
   cd student-grade-system
Download SQLite JDBC Driver
bash
   # Direct download link
   wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar
Or download manually from Maven Repository

Compile Windows:
bash
   javac -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem.java
Mac/Linux:

bash
   javac -cp ".:sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem.java
Run Windows:
bash
   java -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem
Mac/Linux:

bash
   java -cp ".:sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem
🚀 Quick Start
bash
# Navigate to project directory
cd student-grade-system

# Compile the program
javac -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem.java

# Run the application
java -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem
💡 Usage
Adding a Student
Enter your choice: 1
Enter Student ID: ST001
Enter Student Name: John Doe
Enter Subject: Mathematics
Enter Marks (0-100): 95
✓ Student added successfully! Grade: A+
Viewing All Students
Enter your choice: 2

==================================================
                  ALL STUDENTS
==================================================
Student ID   Name          Subject         Marks    Grade
--------------------------------------------------
ST001        John Doe      Mathematics     95       A+
ST002        Jane Smith    Physics         82       A
ST003        Bob Johnson   Chemistry       68       C
==================================================
Total Students: 3
View Statistics
Enter your choice: 7

==================================================
              CLASS STATISTICS
==================================================
Total Students    : 3
Average Marks     : 81.67
Highest Marks     : 95
Lowest Marks      : 68

Grade Distribution:
  A+ Grade        : 1
  A Grade         : 1
  B Grade         : 0
  C Grade         : 1
  F Grade         : 0
==================================================
📁 Project Structure
student-grade-system/
├── StudentGradeSystem.java       # Main application file
├── sqlite-jdbc-3.36.0.3.jar     # SQLite JDBC driver
├── student_grades.db            # Database (auto-created)
├── README.md                    # This file
└── LICENSE                      # License file
🗄️ Database Schema
sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    subject TEXT NOT NULL,
    marks INTEGER NOT NULL,
    grade TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
🎓 What I Learned
✅ Java Programming: OOP concepts, exception handling, input validation
✅ Database Connectivity: JDBC API, connection management
✅ SQL: DDL, DML, aggregate functions
✅ CRUD Operations: Complete implementation of Create, Read, Update, Delete
✅ Security: Using PreparedStatement to prevent SQL injection
✅ Error Handling: Robust exception management
✅ User Interface: Menu-driven console application design
🔧 Technical Highlights
Database Connection
java
// Explicit driver loading
Class.forName("org.sqlite.JDBC");
connection = DriverManager.getConnection("jdbc:sqlite:student_grades.db");
CRUD with PreparedStatement
java
// Secure parameterized queries
String sql = "INSERT INTO students (student_id, name, subject, marks, grade) VALUES (?, ?, ?, ?, ?)";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setString(1, studentId);
pstmt.setString(2, name);
// ... etc
pstmt.executeUpdate();
Automatic Grade Calculation
java
private static String calculateGrade(int marks) {
    if (marks >= 90) return "A+";
    else if (marks >= 80) return "A";
    else if (marks >= 70) return "B";
    else if (marks >= 60) return "C";
    else return "F";
}
🐛 Troubleshooting
<details> <summary><b>ClassNotFoundException: org.sqlite.JDBC</b></summary>
Solution:

Ensure sqlite-jdbc-3.36.0.3.jar is in the project directory
Check classpath in compile/run commands
Windows uses ; (semicolon), Mac/Linux uses : (colon)
</details> <details> <summary><b>javac is not recognized</b></summary>
Solution:

Install Java JDK
Add Java to system PATH
Restart terminal/command prompt
</details> <details> <summary><b>Duplicate Student ID Error</b></summary>
Solution:

This is expected behavior
Student IDs must be unique
Use different IDs or update existing records
</details>
🚀 Future Enhancements
Planned Features
 GUI using JavaFX/Swing
 Export data to CSV/Excel
 Generate PDF report cards
 Multiple subjects per student
 Attendance tracking
 User authentication
 REST API development
 Web interface using Spring Boot
 Charts and graphs visualization
Advanced Ideas
 Mobile app version
 Cloud deployment
 Email notifications
 Integration with LMS platforms
 Multi-language support
📊 Statistics
Lines of Code: ~370
Functions: 10+
Database Tables: 1
CRUD Operations: 4 (Complete)
🤝 Contributing
Contributions, issues, and feature requests are welcome!

Fork the project
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request
📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

👤 Author
A. Nazeeha

🎓 Final Year B.Sc. Computer Science Student
🏫 St. Antony's College of Arts and Science for Women, Dindigul
📧 Email: nazeeha2406@gmail.com
💼 LinkedIn: linkedin.com/in/a-nazeeha
🌐 GitHub: @yourusername
Current Learning
☁️ AWS Cloud Practitioner (Exam Scheduled)
📊 Big Data Analytics
☕ Advanced Java Programming
🙏 Acknowledgments
Inspired by real-world student management needs
Thanks to the Java and SQLite communities
Built as part of practical learning and skill development
📞 Support
If you find this project helpful:

⭐ Star this repository
🐛 Report bugs via Issues
💡 Suggest features via Pull Requests
📧 Contact me for questions
<div align="center">
⭐ Star this repo if you found it helpful!
Made with ❤️ by Nazeeha

Show Image

</div>
📚 Related Projects
Java CRUD Examples
SQLite Database Projects
Student Management Systems
Last Updated: February 2026
Version: 1.0.0
Status: ✅ Active Development

