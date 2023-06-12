package com.project.crud.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CourseDTO {

    private Long id;

    private String name;

    private String description;

    private String professor;
   
    private LocalDate startDate;

    private LocalDate endDate;
}

