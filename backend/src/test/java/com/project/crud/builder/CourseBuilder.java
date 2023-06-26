package com.project.crud.builder;

import java.time.LocalDate;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.model.Course;

public class CourseBuilder {

public static Course createCourse(){
    return new Course(1L, "Course", "Description", "Professor Name",
                         LocalDate.of(2023, 01, 01), LocalDate.of(2023, 06, 01), null, null, null);
} 

public static CourseDTO creaCourseDTO(){
    return new CourseDTO(1L, "Course", "Description", "Professor Name",
                         LocalDate.of(2023, 01, 01), LocalDate.of(2023, 06, 01));
}

}
    

