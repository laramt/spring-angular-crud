package com.project.crud.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.crud.dtos.StudentDTO;
import com.project.crud.model.Student;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ModelMapper mapper;

    public Student toStudent(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }

    public StudentDTO toStudentDTO(Student entity){
        return mapper.map(entity, StudentDTO.class);
    }

    public List<StudentDTO> toStudentDTOList(List<Student> students) {
        return students.stream()
                .map(this::toStudentDTO)
                .collect(Collectors.toList());
    }

    public Student updateStudentFromStudentDTO(Student entity, StudentDTO dto){
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(dto, entity);
        return entity;
    }
    
}
