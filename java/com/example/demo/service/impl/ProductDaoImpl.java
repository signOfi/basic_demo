package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProduct(Long productId, Long categoryId) {
        Product product =  productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("product", "productId", productId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId));
        if (product.getCategory().getId().equals(category.getId())) {
            return product;
        } else {
            throw new ResourceNotFoundException("product/category mismatch", "mismatch", categoryId);
        }
    }

    @Override
    public Product createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId) );
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product, Long categoryId) {

        Product updatedProduct = productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("product", "productId", productId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId));
        if (product.getCategory().getId().equals(category.getId())) {
            updatedProduct.setCategory(category);
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            productRepository.save(updatedProduct);
            return updatedProduct;
        } else {
            throw new ResourceNotFoundException("product/category mismatch", "mismatch", categoryId);
        }
    }

    @Override
    public void deleteProduct(Long productId, Long categoryId) {
        Product product =  productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("product", "productId", productId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new ResourceNotFoundException("category", "categoryId", categoryId));
        if (product.getCategory().getId().equals(category.getId())) {
            productRepository.delete(product);
        } else {
            throw new ResourceNotFoundException("product/category mismatch", "mismatch", categoryId);
        }
    }
}
