package com.leonardo.projetoanalisevendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.projetoanalisevendas.models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
