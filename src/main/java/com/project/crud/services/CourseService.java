package com.project.crud.services;

import java.util.List;

import com.project.crud.dtos.CourseDTO;

public interface CourseService {
    
    CourseDTO insert(CourseDTO dto);
    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    void delete(Long id);
    CourseDTO update(Long id, CourseDTO dto);
    
}
