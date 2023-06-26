package com.project.crud.services.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.project.crud.repositories.CourseRepository;


@SpringBootTest
public class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private CourseMapper mapper;

    @Mock
    private StudentMapper studentMapper;

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
        course.setName(null);
        course.setProfessor(null);
        course.setDescription(null);

        when(mapper.toCourse(dto)).thenReturn(course);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);

    }

    //@Test
    public void insertCourseWithEmptyFildsShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        course.setName("");
        course.setProfessor("");

        when(mapper.toCourse(dto)).thenReturn(course);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);
    }

    @Test
    public void insertCourseWithEndDateBeforeStartDateShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        course.setStartDate(LocalDate.of(2023, 02, 01));
        course.setEndDate(LocalDate.of(2023, 01, 01));
        
        when(mapper.toCourse(dto)).thenReturn(course);

        // Assert
        assertThrows(InvalidInputException.class, () -> service.insert(dto));
        verify(mapper).toCourse(dto);
    }

    @Test
    public void insertCourseWithDescriptionShorterThan100CharactersShouldThrowInvalidInputException(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        course.setDescription("Short Description");
        
        when(mapper.toCourse(dto)).thenReturn(course);

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

    @Test
    public void findAllShouldReturnACourseDTOList(){
        // Arrange
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        List<Course> courses = new ArrayList<>();
        List<CourseDTO> courseDtos = new ArrayList<>();
        courses.add(course);
        courseDtos.add(dto);

        when(mapper.toCourseDTOList(courses)).thenReturn(courseDtos);
        when(repository.findAll()).thenReturn(courses);

        // Act
        List<CourseDTO> result = service.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(courses.get(0).getId(),result.get(0).getId());
        assertEquals(courses.get(0).getName(), result.get(0).getName());
    }


    @Test
    public void findByIdShouldReturnCourseDTOWhenIExists(){
        // Arrange
        Long id = 1L;
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();

        when(repository.findById(id)).thenReturn(Optional.of(course));
        when(mapper.toCourseDTO(course)).thenReturn(dto);

        // Act
        CourseDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(mapper).toCourseDTO(course);
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

    @Test
    public void deleteShouldReturnNothingWhenIdExists(){
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new Course()));
        doNothing().when(repository).deleteById(id);

        // Assert
        assertDoesNotThrow(() -> service.delete(id));
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
         // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, ()-> service.delete(id));
    }

    @Test 
    public void updateShouldReturnCourseDTOWhenIdExists(){
        // Arrange
        Long id = 1L;
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        
        when(repository.findById(id)).thenReturn(Optional.of(course));
        when(mapper.updateStudentFromStudentDTO(course, dto)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);
        when(mapper.toCourseDTO(course)).thenReturn(dto);

        // Act
        CourseDTO result = service.update(id, dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getProfessor(), result.getProfessor());
        assertEquals(dto.getStartDate(), result.getStartDate());

        verify(mapper).toCourseDTO(course);
        verify(repository).save(course);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExeptionWhenIdDoesNotExist(){
        // Arrange
        Long id = 1L;
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        when(repository.findById(id)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> service.update(id, dto));

    }

    //@Test
    public void updateShouldThrowInvalidInputExceptionWhenFildsAreEmpty(){
        // Arrange
        Long id = 1L;
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        course.setName("");
        course.setProfessor("");
        
        when(repository.findById(id)).thenReturn(Optional.of(course));

        // Assert
        assertThrows(InvalidInputException.class, () -> service.update(id, dto));
    }

    //@Test
    public void updateShouldThrowInvalidInputExceptionWhenStartDateIsAfterDueDate(){
        // Arrange
        Long id = 1L;
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        course.setStartDate(LocalDate.of(2023, 02, 01));
        course.setEndDate(LocalDate.of(2023, 01, 01));
        
        when(repository.findById(id)).thenReturn(Optional.of(course));

        // Assert
        assertThrows(InvalidInputException.class, () -> service.update(id, dto));
    }

    @Test
    public void getStudentByCourseShouldReturnListOfStudentDTOs(){
        // Arrange
        Long id = 1L;
        Course course = CourseBuilder.createCourse();
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        course.getStudents().add(student);
        studentDTOs.add(dto);

        when(repository.findById(id)).thenReturn(Optional.of(course));
        when(studentMapper.toStudentDTOList(course.getStudents())).thenReturn(studentDTOs);

        // Act
        List<StudentDTO> result = service.getStudentsByCourseId(id);

        // Assert
        assertNotNull(result);
        assertEquals(1, course.getStudents().size());
        assertEquals(1, result.size());
        assertEquals(course.getStudents().get(0).getName(), result.get(0).getName());

    }

    @Test
    public void getStudentsByCourseShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        // Arrange
        Long id = 1L;

        when(repository.findById(id)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> service.getStudentsByCourseId(id));
    }
}
