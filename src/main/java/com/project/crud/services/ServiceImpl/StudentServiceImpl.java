package com.project.crud.services.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.crud.dtos.StudentDTO;
import com.project.crud.exceptions.ResourceNotFoundException;
import com.project.crud.mappers.StudentMapper;
import com.project.crud.model.Student;
import com.project.crud.repositories.StudentRepository;
import com.project.crud.services.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentDTO insert(StudentDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<StudentDTO> findAll() {
        return mapper.toStudentDTOList(repository.findAll());
    }

    @Override
    public StudentDTO findById(Long id) {
       Student student  = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));
       return mapper.toCourseDTO(student);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
