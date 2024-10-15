package com.example.demo.controller;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Category;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> getCategory(
            @PathVariable("id") Long id
    ) {
        Category category = categoryDao.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Category> createCategory(
            @RequestBody Category category
    ) {
        Category category1 = categoryDao.createCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> updateMapping (
            @RequestBody Category category,
            @PathVariable long id
    ) {
        Category category1 = categoryDao.updateCategory(id, category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Category> deleteMapping(
            @PathVariable long id
    ) {
        categoryDao.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
