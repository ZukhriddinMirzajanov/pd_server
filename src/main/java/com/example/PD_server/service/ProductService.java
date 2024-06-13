package com.example.PD_server.service;

import com.example.PD_server.dto.ProductDto;
import com.example.PD_server.model.Product;
import com.example.PD_server.model.User;
import com.example.PD_server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getUserProducts(String username) {
        return productRepository.findByUserUsername(username).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void addProduct(ProductDto productDto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = new Product();
        product.setProductCode(productDto.getProductCode());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductImage(productDto.getProductImage());
        product.setUser(user);
        productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductDto productDto, String username) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to update this product");
        }
        product.setProductCode(productDto.getProductCode());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductImage(productDto.getProductImage());
        productRepository.save(product);
    }

    public void deleteProduct(Long productId, String username) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to delete this product");
        }
        productRepository.delete(product);
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getProductCode(), product.getProductName(), product.getProductPrice(), product.getProductImage());
    }
}
