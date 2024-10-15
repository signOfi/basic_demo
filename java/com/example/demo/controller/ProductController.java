package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<List<Product>> getAllProducts(
            @PathVariable Long categoryId
    ) {
        List<Product> products = productDao.getAllProducts(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}/{categoryId}")
    ResponseEntity<Product> getProduct(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId
    ) {
        Product product = productDao.getProduct(productId, categoryId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/{categoryId}")
    ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            @PathVariable("categoryId") Long categoryId
    ) {
        Product product1 = productDao.createProduct(product, categoryId);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/{categoryId}")
    ResponseEntity<Product> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody Product updatedProduct,
            @PathVariable("categoryId") Long categoryId
    ) {
        Product product = productDao.updateProduct(productId, updatedProduct, categoryId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/{categoryId}")
    ResponseEntity<Void> deleteProduct(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId
    ) {
        productDao.deleteProduct(productId, categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
