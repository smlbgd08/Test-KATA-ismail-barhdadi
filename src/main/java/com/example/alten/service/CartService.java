package com.example.alten.service;

import com.example.alten.entity.Cart;
import com.example.alten.entity.CartItem;
import com.example.alten.entity.Product;
import com.example.alten.entity.User;
import com.example.alten.repository.CartRepository;
import com.example.alten.repository.ProductRepository;
import com.example.alten.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart getCartByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    public Cart addToCart(String email, Long productId, int quantity) {
        Cart cart = getCartByUser(email);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        return cartRepository.save(cart);
    }

    public void clearCart(String email) {
        Cart cart = getCartByUser(email);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
