package com.example.alten.controller;

import com.example.alten.JwtUtil.JwtUtil;
import com.example.alten.entity.Wishlist;
import com.example.alten.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<Wishlist> addToWishlist(@RequestHeader(name = "Authorization") String token, @RequestParam Long productId) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        return ResponseEntity.ok(wishlistService.addToWishlist(email, productId));
    }

    @GetMapping
    public ResponseEntity<Wishlist> getWishlist(@RequestHeader(name = "Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        return ResponseEntity.ok(wishlistService.getWishlistByUser(email));
    }

    @DeleteMapping("")
    public ResponseEntity<String> removeFromWishlist(@RequestHeader(name = "Authorization") String token, @RequestParam Long productId) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        wishlistService.removeFromWishlist(email, productId);
        return ResponseEntity.ok("Produit supprimé de la liste d'envie.");
    }
}