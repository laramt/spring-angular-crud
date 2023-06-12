package com.project.crud.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 100, max = 500, message = "Description must be between {min} and {max} characters.")
    private String description;

    @NotBlank
    private String professor;
   
    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}

