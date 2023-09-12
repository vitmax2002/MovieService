package com.example.movieservice.controller;

import com.example.movieservice.model.Category;
import com.example.movieservice.model.FilmCategory;
import com.example.movieservice.model.dto.FilmCategoryDto;
import com.example.movieservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
         Category createdCategory=categoryService.addCategory(category);
         return ResponseEntity.ok().body(createdCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> removeCategory(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category category){
        Category updatedCategory=categoryService.updateCategory(categoryId,category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId){
        Category category=categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok().body(category);
    }
}
