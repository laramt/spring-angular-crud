package com.project.crud.services;

import java.util.List;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.dtos.StudentDTO;

public interface CourseService {
    
    CourseDTO insert(CourseDTO dto);
    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    void delete(Long id);
    CourseDTO update(Long id, CourseDTO dto);
    List<StudentDTO> getStudentsByCourseId(Long courseId);
    
}
