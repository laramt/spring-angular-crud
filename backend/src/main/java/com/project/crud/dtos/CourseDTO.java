package com.project.crud.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    private String name;
   
    private String description;

    private String professor;
    
    private LocalDate startDate;

    private LocalDate endDate;
}

