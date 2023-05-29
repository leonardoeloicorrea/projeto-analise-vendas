package com.leonardo.projetoanalisevendas.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.projetoanalisevendas.dtos.ProductDTO;
import com.leonardo.projetoanalisevendas.enums.Category;
import com.leonardo.projetoanalisevendas.models.Product;
import com.leonardo.projetoanalisevendas.repositories.ProductRepository;
import com.leonardo.projetoanalisevendas.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(product -> new ProductDTO(product));
    }

    public ProductDTO findProductById(Long id) {
        Product entity = findEntity(id);
        return new ProductDTO(entity);
    }

    public Page<ProductDTO> findProductsByCategory(String category, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(Category.valueOf(category.toUpperCase()), pageable);
        return products.map(product -> new ProductDTO(product));
    }

    public ProductDTO insertProduct(ProductDTO productDTO) {
        Product entity = productDTO.convertDtoToEntity(productDTO);
        entity.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        entity.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        productRepository.save(entity);
        return new ProductDTO(entity);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product entity = findEntity(id);
        entity = productDTO.updateEntity(entity, productDTO);
        entity.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        productRepository.save(entity);
        return new ProductDTO(entity);
    }

    public void deleteProduct(Long id) {
        findEntity(id);
        productRepository.deleteById(id);
    }
    
    protected Product findEntity(Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
