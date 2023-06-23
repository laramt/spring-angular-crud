package com.project.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
