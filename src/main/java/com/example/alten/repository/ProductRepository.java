package com.example.alten.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alten.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
