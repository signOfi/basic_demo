package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts(Long categoryId);
    Product getProduct(Long productId, Long categoryId);
    Product createProduct(Product product, Long categoryId);
    Product updateProduct(Long productId, Product product, Long categoryId);
    void deleteProduct(Long productId, Long categoryId);
}
