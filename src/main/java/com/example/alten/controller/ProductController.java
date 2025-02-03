package com.example.alten.controller;

import java.util.List;

import com.example.alten.JwtUtil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.alten.dto.ProductDTO;
import com.example.alten.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO , @RequestHeader (name="Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Accès refusé : Seul l'administrateur peut ajouter un produit.");
        }

        // Si l'email est valide, créer le produit
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<String>(createdProduct.getCode(), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestHeader (name="Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        if (!"admin@admin.com".equals(email)) {
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<List<ProductDTO>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id, @RequestHeader (name="Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        if (!"admin@admin.com".equals(email)) {
            return new ResponseEntity<ProductDTO>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ProductDTO>(productService.getProductById(id), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO, @RequestHeader (name="Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        if (!"admin@admin.com".equals(email)) {
            return new ResponseEntity<ProductDTO>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ProductDTO>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @RequestHeader (name="Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        if (!"admin@admin.com".equals(email)) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
