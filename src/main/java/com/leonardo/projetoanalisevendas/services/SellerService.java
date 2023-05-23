package com.leonardo.projetoanalisevendas.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.projetoanalisevendas.dtos.SellerDTO;
import com.leonardo.projetoanalisevendas.models.Seller;
import com.leonardo.projetoanalisevendas.repositories.SellerRepository;
import com.leonardo.projetoanalisevendas.services.exceptions.DatabaseException;
import com.leonardo.projetoanalisevendas.services.exceptions.ResourceNotFoundException;

@Service
public class SellerService {
    
    @Autowired
    private SellerRepository sellerRepository;

    public Page<SellerDTO> findAllSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable).map(seller -> new SellerDTO(seller));
    }

    public SellerDTO findSellerById(Long id) {
        Seller entity = sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
        return new SellerDTO(entity);
    }

    public SellerDTO insertSeller(SellerDTO sellerDTO) {
        
            Seller entity = sellerDTO.convertDtoToEntity(sellerDTO);
            entity.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
            
            validateUniqueData(sellerDTO);

            sellerRepository.save(entity);
            return new SellerDTO(entity);

    }

    public SellerDTO updateSeller(Long id, SellerDTO sellerDTO) {
        validateUniqueData(sellerDTO);
        Seller entity = sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
        entity = sellerDTO.updateEntity(entity, sellerDTO);
        sellerRepository.save(entity);
        return new SellerDTO(entity);
    }

    public void deleteSeller(Long id) {
        findSellerById(id);
        sellerRepository.deleteById(id);
    }

    private void validateUniqueData(SellerDTO sellerDTO) {
        if(sellerRepository.existsByCpf(sellerDTO.getCpf())){
            throw new DatabaseException("The entered cpf already exists");
        }
        if(sellerRepository.existsByEmail(sellerDTO.getEmail())){
            throw new DatabaseException("The entered email already exists");
        }
    }
}
