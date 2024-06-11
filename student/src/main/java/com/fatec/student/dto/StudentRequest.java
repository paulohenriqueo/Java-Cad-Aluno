package com.fatec.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
    
@NotNull
String name, 

@NotBlank
String course) {

}
