package com.project.crud.builder;

import java.time.LocalDate;


import com.project.crud.dtos.StudentDTO;
import com.project.crud.model.Student;

public class StudentBuilder {

    public static Student createStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Student Name");
        student.setEmail("student@email.com");
        student.setPhoneNumber("9999-00000");
        student.setBirthDate(LocalDate.of(1998, 12, 05));
        student.setMajor("Major");
        return student;
    }

    public static StudentDTO createStudentDTO() {
        StudentDTO student = new StudentDTO();
        student.setId(1L);
        student.setName("Student Name");
        student.setEmail("student@email.com");
        student.setPhoneNumber("9999-00000");
        student.setBirthDate(LocalDate.of(1998, 12, 05));
        student.setMajor("Major");
        return student;
    }
    
}
