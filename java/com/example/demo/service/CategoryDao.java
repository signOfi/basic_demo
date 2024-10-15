package com.example.demo.service;
import com.example.demo.entity.Category;

import java.util.*;

public interface CategoryDao {
    List<Category> getAllCategories();
    Category getCategory(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
