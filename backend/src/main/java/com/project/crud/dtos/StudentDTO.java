package com.project.crud.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private String phoneNumber;

    private String major;
    
}
