package com.project.crud.services.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.exceptions.InvalidInputException;
import com.project.crud.exceptions.ResourceNotFoundException;
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
    @Transactional
    public CourseDTO insert(CourseDTO dto) {
        Course course = mapper.toCourse(dto);
        if(course.getName() == null || course.getDescription() == null || course.getProfessor() == null){
            throw new InvalidInputException("Course filds cannot be null.");
        }

        if(course.getDescription().length() < 100 || course.getDescription().length() > 500){
            throw new InvalidInputException("Description must be between 100 and 500 characters.");
        }

        if(course.getEndDate().isBefore(course.getStartDate())){
            throw new InvalidInputException("End date must be after start date.");
        }

        repository.save(course);
        return mapper.toCourseDTO(course);
    }

    @Override
    public List<CourseDTO> findAll() {
        return mapper.toCourseDTOList(repository.findAll());
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));
        
        return mapper.toCourseDTO(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));

        repository.deleteById(id);
    }

    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
