package com.project.crud.services.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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
import com.project.crud.repositories.CourseRepository;
import com.project.crud.repositories.StudentRepository;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private CourseRepository courseRepository;

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

    @Test
    public void deleteShouldReturnNothingWhenIdExists(){
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new Student()));
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
    public void updateShouldReturnStudentDTOWhenIdExists(){
        // Arrange
        Long id = 1L;
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();

        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(mapper.toStudentDTO(student)).thenReturn(dto);
        when(mapper.updateStudentFromStudentDTO(student, dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);

        // Act
        StudentDTO result = service.update(id, dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getName(), result.getName());


        verify(mapper).toStudentDTO(student);
        verify(repository).save(student);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExeptionWhenIdDoesNotExist(){
        // Arrange
        Long id = 1L;
        StudentDTO dto = StudentBuilder.createStudentDTO();
        when(repository.findById(id)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> service.update(id, dto));

    }


    //@Test
    public void updateShouldThrowInvalidInputExceptionWhenFildsAreEmpty(){
        // Arrange
        Long id = 1L;
        Student student = StudentBuilder.createStudent();
        StudentDTO dto = StudentBuilder.createStudentDTO();
        student.setName("");
        student.setMajor("");
        
        when(repository.findById(id)).thenReturn(Optional.of(student));

        // Assert
        assertThrows(InvalidInputException.class, () -> service.update(id, dto));
    }

    @Test
    public void getCourseByStudenthouldReturnListOfCourseDTOs(){
        // Arrange
        Long id = 1L;
        Student student = StudentBuilder.createStudent();
        Course course = CourseBuilder.createCourse();
        CourseDTO dto = CourseBuilder.creaCourseDTO();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        student.getCourses().add(course);
        courseDTOs.add(dto);

        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(courseMapper.toCourseDTOList(student.getCourses())).thenReturn(courseDTOs);

        // Act
        List<CourseDTO> result = service.getCoursesByStudentId(id);

        // Assert
        assertNotNull(result);
        assertEquals(1, student.getCourses().size());
        assertEquals(1, result.size());
        assertEquals(student.getCourses().get(0).getName(), result.get(0).getName());

    }

    @Test
    public void addCourseToStudentShoudReturnVoidWhenRequestIdValid(){
        // Arrange
        Long studentId = 1L;
        Long courseId = 1L;
        Student student = StudentBuilder.createStudent();
        Course course = CourseBuilder.createCourse();

        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(repository.save(student)).thenReturn(student);
        when(courseRepository.save(course)).thenReturn(course);
        
        // Act
        service.addCoursesToStudent(studentId, courseId);
        
        // Assert
        assertNotNull(student.getCourses());
        assertEquals(1, student.getCourses().size());

    }

    @Test
    public void addCoursesToStudentShouldThrowResourceNotFoundExceptionWhenStudentIdDoesNotExist(){
        // Arrange
        Long studentId = 1L;
        Long courseId = 1L;
        Student student = StudentBuilder.createStudent();

        when(repository.findById(studentId)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> service.addCoursesToStudent(studentId, courseId));
    }

    @Test
    public void addCoursesToStudentShouldThrowResourceNotFoundExceptionWhenCourseIdDoesNotExist(){
        // Arrange
        Long studentId = 1L;
        Long courseId = 1L;
        Student student = StudentBuilder.createStudent();
        Course course = CourseBuilder.createCourse();

        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> service.addCoursesToStudent(studentId, courseId));
    }


}
