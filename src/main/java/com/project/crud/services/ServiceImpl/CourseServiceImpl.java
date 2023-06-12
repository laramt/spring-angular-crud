package com.project.crud.services.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.mappers.CourseMapper;
import com.project.crud.model.Course;
import com.project.crud.repositories.CourseRepository;
import com.project.crud.services.CourseService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public CourseDTO insert(CourseDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<CourseDTO> findAll() {
        return mapper.toCourseDTOList(repository.findAll());
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No course with id: " + id));
        
        return mapper.toCourseDTO(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No course with id: " + id));
            
        repository.deleteById(id);
    }

    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
