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

import com.leonardo.projetoanalisevendas.dtos.SellerDTO;
import com.leonardo.projetoanalisevendas.services.SellerService;
import com.leonardo.projetoanalisevendas.services.exceptions.DatabaseException;
import com.leonardo.projetoanalisevendas.services.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {
    
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<Page<SellerDTO>> findAllSellers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findAllSellers(pageable));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SellerDTO> findSellerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findSellerById(id));
    }

    @PostMapping
    public ResponseEntity<SellerDTO> insertSeller(@RequestBody @Valid SellerDTO sellerDTO){
            return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(sellerDTO));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable Long id, @RequestBody SellerDTO sellerDTO){
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSeller(id, sellerDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteSeller(@PathVariable Long id) {
            sellerService.deleteSeller(id);
            return ResponseEntity.status(HttpStatus.OK).body("Seller removed successfully");

    }
}