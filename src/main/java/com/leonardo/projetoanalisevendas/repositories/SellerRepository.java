package com.leonardo.projetoanalisevendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.projetoanalisevendas.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}
