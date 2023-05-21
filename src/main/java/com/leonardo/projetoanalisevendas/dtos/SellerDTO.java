package com.leonardo.projetoanalisevendas.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @NotBlank
    private String name;

    @Size(max = 3, message = "Age must be between 1 and 3 characters")
    @NotNull(message = "Required field")
    @Positive(message = "Age must be greater than 0")
    private Integer age;

    
    @NotBlank(message = "Required field")
    private String gender;

    @Size(min = 11, max = 11, message = "The cpf must have 11 characters")
    @NotNull(message = "Required field")
    private String cpf;

    @Size(min = 10, max = 20, message = "Mobile number must be between 10 and 20 characters")
    @NotBlank(message = "Required field")
    private String phoneNumber;

    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    @NotBlank(message = "Required field")
    @Email(message = "Invalid email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Required field")
    private LocalDate dateOfBirth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Required field")
    private LocalDate hiringDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @NotNull(message = "Required field")
    private LocalDateTime updateDate;
    
}
