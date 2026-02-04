🎓 Student Grade Management System
----------------------------------------------------



A Java-based console application for managing student grades using SQLite.
Supports full CRUD operations, automatic grade calculation, and class-level statistics.

---------------------------------------------------------

✨ Features


📝 Add Students – Register students with automatic grade calculation

👁️ View Records – Display all students in formatted tables

🔍 Search Student – Find students by unique ID

✏️ Update Marks – Modify marks with auto grade recalculation

🗑️ Delete Records – Remove student data with confirmation

📊 Statistics – Class average, highest/lowest marks, grade distribution

💾 Persistent Storage – Data stored using SQLite database

------------------------------------------------------------------

🎯 Grading System


Grade	                  Marks Range

A+	                     (90 – 100)

A	                       (80 – 89)
    
B	                       (70 – 79)

C	                        (60 – 69)
  
F	                       (Below 60)

---------------------------------------------------------------------

🛠️ Tech Stack

Language: Java

Database: SQLite 3.36.0.3

API: JDBC

Architecture: MVC-inspired

-------------------------------------------------------------------------

📦 Installation
Prerequisites

Java JDK 8 or higher

SQLite JDBC Driver

----------------------------------------------------------------------------

Clone Repository

git clone https://github.com/nazeeha2406-star/STUDENT-GRADE-MANAGEMENT-SYSTEM.git

cd STUDENT-GRADE-MANAGEMENT-SYSTEM

-----------------------------------------------------------------------------------------


Download SQLite JDBC Driver

wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar


Or download manually from Maven Repository.

----------------------------------------------------------------------------------------------
▶️ Compile & Run

Windows

javac -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem.java

java -cp ".;sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem

Mac / Linux

javac -cp ".:sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem.java

java -cp ".:sqlite-jdbc-3.36.0.3.jar" StudentGradeSystem

--------------------------------------------------------------------------------------------------

💡 Usage Example

Add Student

Enter choice: 1

Student ID: ST001

Name: John Doe

Subject: Mathematics

Marks: 95

✔ Student added successfully (Grade: A+)

-------------------------------------------------------------------------------------------------------------


View Statistics

Total Students: 3

Average Marks: 81.67

Highest Marks: 95

Lowest Marks: 68

------------------------------------------------------------------------------------------------------------


📁 Project Structure

STUDENT-GRADE-MANAGEMENT-SYSTEM/

├── StudentGradeSystem.java

├── sqlite-jdbc-3.36.0.3.jar

├── student_grades.db

├── README.md

└── LICENSE

----------------------------------------------------------------------------------------------------------------

🗄️ Database Schema

CREATE TABLE students
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  student_id TEXT UNIQUE NOT NULL,
  name TEXT NOT NULL,
  subject TEXT NOT NULL,
  marks INTEGER NOT NULL,
  grade TEXT NOT NULL,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--------------------------------------------------------------------------------------------------

🔧 Technical Highlights

JDBC Connection

Class.forName("org.sqlite.JDBC");

Connection conn = DriverManager.getConnection("jdbc:sqlite:student_grades.db");

--------------------------------------------------------------------------------------

Secure Insert Query

String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";

PreparedStatement pstmt = conn.prepareStatement(sql);

-----------------------------------------------------------------------------------------

Grade Calculation

private static String calculateGrade(int marks) {

    if (marks >= 90) return "A+";
    else if (marks >= 80) return "A";
    else if (marks >= 70) return "B";
    else if (marks >= 60) return "C";
    else return "F";
    
}

----------------------------------------------------------------------------------------

🐛 Troubleshooting


ClassNotFoundException: org.sqlite.JDBC → JDBC jar missing

javac not recognized → Java not added to PATH

Duplicate Student ID → ID must be unique

----------------------------------------------------------------------------

🚀 Future Enhancements

GUI using JavaFX / Swing

Export to CSV / Excel

PDF Report Cards

Multiple subjects per student

REST API with Spring Boot

Cloud deployment

----------------------------------------------------------------------------
👤 Author

A. Nazeeha

🎓 Final Year B.Sc. Computer Science

🏫 St. Antony's College of Arts and Science for Women, Dindigul

📧 Email: nazeeha2406@gmail.com

📝 License

This project is licensed under the MIT License.

⭐ Star this repository if you found it helpful!
Made with ❤️ by Nazeeha
