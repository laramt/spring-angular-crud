package com.project.crud.builder;


import java.time.LocalDate;

import com.project.crud.dtos.CourseDTO;
import com.project.crud.model.Course;

public class CourseBuilder {

public static Course createCourse(){
    Course course = new Course();
    course.setId(1L);
    course.setName("Course Name");
    course.setProfessor("Professor");
    course.setStartDate(LocalDate.of(2023, 01, 01));
    course.setEndDate(LocalDate.of(2023, 05, 01));
    course.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit");

    return course;
} 

public static CourseDTO creaCourseDTO(){
    CourseDTO course = new CourseDTO();
    course.setId(1L);
    course.setName("Course Name");
    course.setProfessor("Professor");
    course.setStartDate(LocalDate.of(2023, 01, 01));
    course.setEndDate(LocalDate.of(2023, 05, 01));
    course.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor elit sit amet, consectetur adipiscing elit");

    return course;
}

public static Course createCourseWithLongerDescription(){
    Course course = new Course();
    course.setId(1L);
    course.setName("Course Name");
    course.setProfessor("Professor");
    course.setStartDate(LocalDate.of(2023, 01, 01));
    course.setEndDate(LocalDate.of(2023, 05, 01));
    course.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor elit sit amet, consectetur adipiscing elit");

    return course;
} 

}