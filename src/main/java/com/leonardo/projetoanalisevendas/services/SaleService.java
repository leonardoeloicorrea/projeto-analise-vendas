package com.leonardo.projetoanalisevendas.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.projetoanalisevendas.dtos.SaleDTO;
import com.leonardo.projetoanalisevendas.models.Product;
import com.leonardo.projetoanalisevendas.models.Sale;
import com.leonardo.projetoanalisevendas.models.Seller;
import com.leonardo.projetoanalisevendas.repositories.SaleRepository;
import com.leonardo.projetoanalisevendas.services.exceptions.ResourceNotFoundException;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    public Page<SaleDTO> findAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable).map(sale -> new SaleDTO(sale));
    }

    public SaleDTO findSaleById(Long id) {
        return new SaleDTO(findEntity(id));
    }

    public SaleDTO insertSale(SaleDTO saleDTO) {
        Sale entity = convertDtoToEntity(saleDTO);
        entity.setDateOfSale(LocalDateTime.now(ZoneId.of("UTC")));
        saleRepository.save(entity);
        return new SaleDTO(entity);
    }

    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale entity = findEntity(id);
        Product product = productService.findEntity(saleDTO.getProductId());
        Seller seller = sellerService.findEntity(saleDTO.getProductId());

        entity.setProduct(product);
        entity.setQuantity(saleDTO.getQuantity());
        entity.setTotalValue(saleDTO.getQuantity() * product.getPrice());
        entity.setSeller(seller);

        saleRepository.save(entity);
        return new SaleDTO(entity);
    }

    public void removeSale(Long id) {
        findEntity(id);
        saleRepository.deleteById(id);
    }












    protected Sale findEntity(Long id) {  
        return saleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
    }

    public Sale convertDtoToEntity(SaleDTO saleDTO) {
        Sale entity = new Sale();
        Product product = productService.findEntity(saleDTO.getProductId());
        Seller seller = sellerService.findEntity(saleDTO.getSellerId());
        entity.setProduct(product);
        entity.setQuantity(saleDTO.getQuantity());
        entity.setTotalValue(saleDTO.getQuantity() * entity.getProduct().getPrice());
        entity.setSeller(seller);
        return entity;
    }

}
