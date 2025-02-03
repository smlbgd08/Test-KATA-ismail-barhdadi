package com.example.alten.controller;

import com.example.alten.JwtUtil.JwtUtil;
import com.example.alten.entity.Cart;
import com.example.alten.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<Cart> addToCart(@RequestHeader(name = "Authorization") String token, @RequestParam Long productId, @RequestParam int quantity) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        return ResponseEntity.ok(cartService.addToCart(email, productId, quantity));
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader(name = "Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        return ResponseEntity.ok(cartService.getCartByUser(email));
    }

    @DeleteMapping("")
    public ResponseEntity<String> clearCart(@RequestHeader(name = "Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        cartService.clearCart(email);
        return ResponseEntity.ok("Panier vidé avec succès.");
    }
}