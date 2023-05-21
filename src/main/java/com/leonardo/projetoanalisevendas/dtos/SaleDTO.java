package com.leonardo.projetoanalisevendas.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dateOfSale;

    @NotBlank(message = "Required field")
    private Long productId;

    @NotNull(message = "Required field")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Required field")
    @Positive(message = "Total value must be greater than 0")
    private Double totalValue;

    @NotNull(message = "Required field")
    private Long saleSellerId;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @NotBlank(message = "Required field")
    private String saleSellerName;

}