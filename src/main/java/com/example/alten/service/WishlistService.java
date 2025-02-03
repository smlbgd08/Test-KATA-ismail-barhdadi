package com.example.alten.service;

import com.example.alten.entity.Product;
import com.example.alten.entity.User;
import com.example.alten.entity.Wishlist;
import com.example.alten.repository.ProductRepository;
import com.example.alten.repository.UserRepository;
import com.example.alten.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Wishlist getWishlistByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return wishlistRepository.findByUser(user).orElseGet(() -> {
            Wishlist newWishlist = new Wishlist();
            newWishlist.setUser(user);
            return wishlistRepository.save(newWishlist);
        });
    }

    public Wishlist addToWishlist(String email, Long productId) {
        Wishlist wishlist = getWishlistByUser(email);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(String email, Long productId) {
        Wishlist wishlist = getWishlistByUser(email);
        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));
        wishlistRepository.save(wishlist);
    }
}