package com.project.crud.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.model.Course;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseMapper {
    
    private final ModelMapper mapper;

    public Course toCourse(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }

    public CourseDTO toCourseDTO(Course entity){
        return mapper.map(entity, CourseDTO.class);
    }

    public List<CourseDTO> toCourseDTOList(List<Course> courses) {
        return courses.stream()
                .map(this::toCourseDTO)
                .collect(Collectors.toList());
    }


    public Course updateStudentFromStudentDTO(Course entity, CourseDTO dto){
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(dto, entity);
        return entity;
    }

}
