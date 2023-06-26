package com.project.crud.services.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.crud.builder.CourseBuilder;
import com.project.crud.dtos.CourseDTO;
import com.project.crud.exceptions.InvalidInputException;
import com.project.crud.mappers.CourseMapper;
import com.project.crud.model.Course;
import com.project.crud.repositories.CourseRepository;

@SpringBootTest
public class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private CourseMapper mapper;

    @InjectMocks
    private CourseServiceImpl service;


    @Test
    public void insertCourseWIthValidRequestShouldReturnCourseDTO(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        
        when(mapper.toCourse(dto)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);
        when(mapper.toCourseDTO(course)).thenReturn(dto);

        // Act
        CourseDTO createdCourse = service.insert(dto);

        // Assert
        assertNotNull(createdCourse);
        assertEquals(dto.getName(), createdCourse.getName());
        assertEquals(dto.getProfessor(), createdCourse.getProfessor());
        assertEquals(dto.getStartDate(), createdCourse.getStartDate());

        verify(mapper).toCourseDTO(course);
        verify(mapper).toCourse(dto);
        verify(repository).save(course);

    }

    @Test
    public void insertCourseWithNullFildsShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(mapper.toCourse(dto)).thenReturn(course);

        course.setName(null);
        course.setProfessor(null);
        course.setDescription(null);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);

    }

    @Test
    public void insertCourseWithEmptyFildsShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(mapper.toCourse(dto)).thenReturn(course);

        course.setName("");
        course.setProfessor("");
        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);
    }

    @Test
    public void insertCourseWithEndDateBeforeStartDateShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(mapper.toCourse(dto)).thenReturn(course);

        course.setStartDate(LocalDate.of(2023, 02, 01));
        course.setEndDate(LocalDate.of(2023, 01, 01));

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);
    }

    @Test
    public void insertCourseWithDescriptionShorterThan100CharactersShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(mapper.toCourse(dto)).thenReturn(course);

        course.setDescription("Short Description");

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);       
    }

    @Test
    public void insertCourseWithDescriptionLongerThan500CharactersShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourseWithLongerDescription();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(mapper.toCourse(dto)).thenReturn(course);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);   
    }

}
