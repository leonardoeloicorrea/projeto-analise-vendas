package com.leonardo.projetoanalisevendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leonardo.projetoanalisevendas.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    
    @Query("SELECT obj "
    + "FROM Seller obj "
    + "WHERE obj.id = :id")
    Page<Seller> findSellerById(@Param("id") String id, Pageable Pageable);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
