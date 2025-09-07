CREATE DATABASE student_management;
USE student_management;
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT,
    course VARCHAR(100),
    enrollment_date DATE
);