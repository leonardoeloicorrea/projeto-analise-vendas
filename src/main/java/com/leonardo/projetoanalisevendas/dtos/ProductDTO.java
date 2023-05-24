package com.leonardo.projetoanalisevendas.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardo.projetoanalisevendas.enums.Category;
import com.leonardo.projetoanalisevendas.models.Product;

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
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdate;

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        description = entity.getDescription();
        category = entity.getCategory().toString();
        creationDate = entity.getCreationDate();
        lastUpdate = entity.getLastUpdate();
    }

    public Product convertDtoToEntity (ProductDTO productDTO){
        Product entity = new Product();
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setDescription(productDTO.getDescription());
        entity.setCategory(Category.valueOf(productDTO.getCategory().toUpperCase()));
        entity.setCreationDate(productDTO.getCreationDate());
        entity.setLastUpdate(productDTO.getLastUpdate());
        return entity;
    }

    public Product updateEntity(Product entity, ProductDTO productDTO) {
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setDescription(productDTO.getDescription());
        entity.setCategory(Category.valueOf(productDTO.getCategory().toUpperCase()));
        entity.setLastUpdate(productDTO.getLastUpdate());
        return entity;
    }
}