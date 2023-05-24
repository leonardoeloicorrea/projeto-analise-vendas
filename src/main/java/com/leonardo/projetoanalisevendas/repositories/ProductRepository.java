package com.leonardo.projetoanalisevendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardo.projetoanalisevendas.enums.Category;
import com.leonardo.projetoanalisevendas.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT obj "
    + "FROM Product obj "
    + "WHERE obj.category = :category")
    Page<Product> findByCategory(Category category, Pageable pageable);

}