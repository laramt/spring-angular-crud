package com.project.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud.model.Course;



public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
