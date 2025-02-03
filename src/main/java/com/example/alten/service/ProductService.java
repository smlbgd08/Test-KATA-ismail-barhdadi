package com.example.alten.service;

import java.util.List;

import com.example.alten.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {
	
	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(Long id);

	ProductDTO createProduct(ProductDTO productDTO);

	ProductDTO updateProduct(Long id, ProductDTO productDTO);

	void deleteProduct(Long id);

}
