package com.project.crud.services;

import java.util.List;

import com.project.crud.dtos.StudentDTO;

public interface StudentService {

    StudentDTO insert(StudentDTO dto);
    List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    void delete(Long id);
    StudentDTO update(Long id, StudentDTO dto);
    
}
