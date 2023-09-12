package com.example.movieservice.service;

import com.example.movieservice.model.Category;
import com.example.movieservice.model.Film;
import com.example.movieservice.model.FilmCategory;
import com.example.movieservice.model.dto.FilmCategoryDto;
import com.example.movieservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        Category createdCategory=categoryRepository.save(category);
        return createdCategory;
    }

    public void deleteCategory(int categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(int categoryId, Category category){
        Category foundCategory=categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException("Nu este category cu indexul: "+categoryId));
        foundCategory.setName(category.getName());
        categoryRepository.save(foundCategory);
        return foundCategory;
    }

    public List<Category> getAllCategories(){
        List<Category> categories=categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryById(int categoryId){
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException("Nu exista category cu id: "+categoryId));
        return category;
    }
}
