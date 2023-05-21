package com.leonardo.projetoanalisevendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.projetoanalisevendas.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}