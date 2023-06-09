package com.leonardo.projetoanalisevendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.projetoanalisevendas.dtos.SaleDTO;
import com.leonardo.projetoanalisevendas.services.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> findSales(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAllSales(pageable));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SaleDTO> findSaleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findSaleById(id));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> insertSale(@RequestBody @Valid SaleDTO saleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insertSale(saleDTO));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.updateSale(id, saleDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> removeSale(@PathVariable Long id) {
        saleService.removeSale(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sale remove successfully");
    }
}