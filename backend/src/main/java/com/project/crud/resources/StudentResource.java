package com.project.crud.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.dtos.StudentDTO;
import com.project.crud.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentResource {

    private final StudentService service;

    @PostMapping(value = "/new")
    public ResponseEntity<StudentDTO> insert(@RequestBody @Valid StudentDTO dto){
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

        @GetMapping(value = "/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> findStudents(@PathVariable Long studentId){
        return ResponseEntity.ok().body(service.getCoursesByStudentId(studentId));
    }

        @PutMapping("/{studentId}/add-course")
    public ResponseEntity<Void> addCoursesToStudent(@PathVariable Long studentId, @RequestParam Long courseId) {
        service.addCoursesToStudent(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
    
}
