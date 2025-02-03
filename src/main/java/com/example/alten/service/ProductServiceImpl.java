package com.example.alten.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.alten.converter.ProductConverter;
import com.example.alten.entity.Product;
import com.example.alten.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.alten.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productConverter.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productConverter.toDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productConverter::toDTO).orElse(null);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = productConverter.toEntity(productDTO);
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            return productConverter.toDTO(updatedProduct);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
