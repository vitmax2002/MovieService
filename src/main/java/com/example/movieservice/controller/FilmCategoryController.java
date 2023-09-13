package com.example.movieservice.controller;

import com.example.movieservice.model.Film;
import com.example.movieservice.model.FilmCategory;
import com.example.movieservice.model.dto.FilmCategoryDto;
import com.example.movieservice.service.FilmCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmcategory")
public class FilmCategoryController {

    private final FilmCategoryService filmCategoryService;

    public FilmCategoryController(FilmCategoryService filmCategoryService) {
        this.filmCategoryService = filmCategoryService;
    }

    @PostMapping
    public ResponseEntity<FilmCategory> addFilmCategory(@Valid @RequestBody FilmCategoryDto filmCategory){
        FilmCategory createdFilmcategory=filmCategoryService.addFilmCategory(filmCategory);
        return ResponseEntity.ok().body(createdFilmcategory);
    }

    @DeleteMapping("/{filmCategoryId}")
    public ResponseEntity<Void> removeFilmCategory(@PathVariable int filmCategoryId){
        filmCategoryService.deleteFilmCategory(filmCategoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{filmCategoryId}")
    public ResponseEntity<FilmCategory> updateFilmCategory(@PathVariable int filmCategoryId,@Valid @RequestBody FilmCategoryDto filmCategoryDto){
        FilmCategory updatedFilmCategory=filmCategoryService.updateFilmCategory(filmCategoryId,filmCategoryDto);
        return ResponseEntity.ok().body(updatedFilmCategory);
    }

    @GetMapping
    public ResponseEntity<List<FilmCategory>> getAllFilmCategories(){
        List<FilmCategory> filmCategories=filmCategoryService.getAllFilmCategories();
        return ResponseEntity.ok().body(filmCategories);
    }

    @GetMapping("/{filmCategoryId}")
    public ResponseEntity<FilmCategory> getFilmCategoryById(@PathVariable int filmCategoryId){
        FilmCategory filmCategory=filmCategoryService.getFilmCategoryById(filmCategoryId);
        return ResponseEntity.ok().body(filmCategory);
    }
}
