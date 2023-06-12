package com.project.crud.services.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.mappers.CourseMapper;
import com.project.crud.repositories.CourseRepository;
import com.project.crud.services.CourseService;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
