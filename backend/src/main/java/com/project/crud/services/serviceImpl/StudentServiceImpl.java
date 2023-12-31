package com.project.crud.services.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

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
import com.project.crud.services.StudentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository repository;
    private final StudentMapper mapper;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    @Override
    public StudentDTO insert(StudentDTO dto) {
         Student student = mapper.toStudent(dto);
        
        if(student.getName() == null || student.getMajor() == null || student.getBirthDate() == null){
            throw new InvalidInputException("Student filds cannot be null.");
        }

         repository.save(student);
         return mapper.toStudentDTO(student);
    }

    @Override
    public List<StudentDTO> findAll() {
        return mapper.toStudentDTOList(repository.findAll());
    }

    @Override
    public StudentDTO findById(Long id) {
       Student student  = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));
       return mapper.toStudentDTO(student);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));
        repository.deleteById(id);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        Student student  = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No course with id: " + id));

            student = mapper.updateStudentFromStudentDTO(student, dto);
            repository.save(student);

        return mapper.toStudentDTO(student);
    }

    @Override
    public List<CourseDTO> getCoursesByStudentId(Long studentId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        return courseMapper.toCourseDTOList(student.getCourses());

    }

    @Transactional
    @Override
    public void addCoursesToStudent(Long studentId, Long courseId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        repository.save(student);
    }
    
}
