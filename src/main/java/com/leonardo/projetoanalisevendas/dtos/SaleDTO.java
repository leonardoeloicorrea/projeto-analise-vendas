package com.leonardo.projetoanalisevendas.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardo.projetoanalisevendas.models.Sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dateOfSale;

    @NotNull(message = "Required field")
    private Long productId;

    @NotNull(message = "Required field")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @Positive(message = "Total value must be greater than 0")
    private Double totalValue;

    @NotNull(message = "Required field")
    private Long sellerId;

    private String sellerName;

    public SaleDTO(Sale entity) {
        id = entity.getId();
        dateOfSale = entity.getDateOfSale();
        productId = entity.getProduct().getId();
        quantity = entity.getQuantity();
        totalValue = entity.getTotalValue();
        sellerId = entity.getSeller().getId();
        sellerName = entity.getSeller().getName();
    }

}