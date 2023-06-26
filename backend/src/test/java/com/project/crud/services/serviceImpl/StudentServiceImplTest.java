package com.project.crud.services.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.crud.builder.CourseBuilder;
import com.project.crud.builder.StudentBuilder;
import com.project.crud.dtos.CourseDTO;
import com.project.crud.dtos.StudentDTO;
import com.project.crud.exceptions.InvalidInputException;
import com.project.crud.exceptions.ResourceNotFoundException;
import com.project.crud.mappers.CourseMapper;
import com.project.crud.mappers.StudentMapper;
import com.project.crud.model.Course;
import com.project.crud.model.Student;
import com.project.crud.repositories.StudentRepository;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper mapper;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private StudentServiceImpl service;

    @Test
    public void insertStudentWIthValidRequestShouldReturnStudentDTO(){
        // Arrange
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();

        when(mapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);
        when(mapper.toStudentDTO(student)).thenReturn(dto);

        // Act
        StudentDTO result = service.insert(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getName(), result.getName());
    }

    @Test
    public void insertCourseWithNullFildsShouldThrowInvalidInputException(){
        // Arrange
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();
        student.setName(null);
        student.setEmail(null);
        student.setBirthDate(null);

        when(mapper.toStudent(dto)).thenReturn(student);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toStudent(dto);

    }

    //@Test
    public void insertCourseWithEmptyFildsShouldThrowInvalidInputException(){
        // Arrange
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();
        student.setName("");
        student.setEmail("");

        when(mapper.toStudent(dto)).thenReturn(student);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toStudent(dto);

    }


    @Test
    public void findAllShouldReturnAStudentDTOList(){
        // Arrange
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();
        List<Student> students = new ArrayList<>();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        students.add(student);
        studentDTOs.add(dto);

        when(mapper.toStudentDTOList(students)).thenReturn(studentDTOs);
        when(repository.findAll()).thenReturn(students);

        // Act
        List<StudentDTO> result = service.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(1, students.size());
        assertEquals(1, result.size());
        assertEquals(students.get(0).getId(),result.get(0).getId());
    
    }

    @Test
    public void findByIdShouldReturnStudentDTOWhenIExists(){
        // Arrange
        Long id = 1L;
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();

        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(mapper.toStudentDTO(student)).thenReturn(dto);

        // Act
        StudentDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(mapper).toStudentDTO(student);
        verify(repository).findById(id);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoudExceptionWhenIdDoesNotExist(){
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenThrow(ResourceNotFoundException.class);

        // Assertion
        assertThrows(ResourceNotFoundException.class, ()-> service.findById(id));
        verify(repository).findById(id);

    }

}
