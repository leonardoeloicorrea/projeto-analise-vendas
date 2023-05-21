package com.leonardo.projetoanalisevendas.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Required field")
    private String name;

    @NotNull(message = "Required field")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Required field")
    private String description;

    @NotBlank(message = "Required field")
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @NotNull(message = "Required field")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @NotNull(message = "Required field")
    private LocalDateTime lastUpdate;
}