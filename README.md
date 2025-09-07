# Student Management System

A console-based Java application using JDBC and MySQL to manage student information.

## Features
- Add a new student
- View all students
- View a student by ID
- Update a student's information
- Delete a student
- Search for students by name

## Setup Instructions
1.  **Database Setup:** Run the `schema.sql` script in MySQL to create the database and table.
2.  **JDBC Driver:** Download the MySQL Connector/J driver and place the JAR file in the `lib` directory.
3.  **Configuration:** Update the database connection details (URL, username, password) in `src/DatabaseConnection.java`.
4.  **Compilation:** Compile the Java code:
    ```bash
    javac -cp ".;lib/mysql-connector-j-8.0.33.jar" -d bin src/*.java
    ```
5.  **Execution:** Run the application:
    ```bash
    java -cp "bin;lib/mysql-connector-j-8.0.33.jar" StudentManagementApp
    ```

## Project Structure
```
Student-Management-System/
├── src/                 # Java source files
├── lib/                 # Libraries (add JDBC JAR here)
├── bin/                 # Compiled Java classes (auto-generated)
├── .gitignore          # Files to ignore in Git
└── README.md           # This file
```