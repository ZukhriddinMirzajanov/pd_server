package com.example.PD_server.controller;

import com.example.PD_server.dto.ProductDto;
import com.example.PD_server.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/user")
    public ResponseEntity<List<ProductDto>> getUserProducts(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(productService.getUserProducts(username));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto, Authentication authentication) {
        String username = authentication.getName();
        productService.addProduct(productDto, username);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto, Authentication authentication) {
        String username = authentication.getName();
        productService.updateProduct(productId, productDto, username);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId, Authentication authentication) {
        String username = authentication.getName();
        productService.deleteProduct(productId, username);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
