package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDaoImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("category", "id", id));
    }

    @Override
    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        /* Get the category by id */
        Category current = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("categoryUpdate", "id", id));

        /* Update the fields */
        current.setDescription(category.getDescription());
        current.setName(category.getName());
        current.setProductList(category.getProductList());

        /* Persist in database */
        categoryRepository.save(current);
        return current;
    }

    @Override
    public void deleteCategory(Long id) {
        /* Get the category by id */
        Category current = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("categoryUpdate", "id", id));

        /* Delete the current */
        categoryRepository.delete(current);
    }
}
