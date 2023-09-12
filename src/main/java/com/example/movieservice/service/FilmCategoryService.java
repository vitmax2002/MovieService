package com.example.movieservice.service;

import com.example.movieservice.model.Category;
import com.example.movieservice.model.Film;
import com.example.movieservice.model.FilmCategory;
import com.example.movieservice.model.dto.FilmCategoryDto;
import com.example.movieservice.repository.CategoryRepository;
import com.example.movieservice.repository.FilmCategoryRepository;
import com.example.movieservice.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FilmCategoryService {

    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;

    public FilmCategoryService(FilmCategoryRepository filmCategoryRepository, FilmRepository filmRepository, CategoryRepository categoryRepository) {
        this.filmCategoryRepository = filmCategoryRepository;
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
    }

    public FilmCategory addFilmCategory(FilmCategoryDto filmCategoryDto){
        FilmCategory filmCategory =new FilmCategory();
        Film film =filmRepository.findById(filmCategoryDto.filmId()).orElseThrow(()->new NoSuchElementException("Nu exista film cu idul dat"));
        Category category =categoryRepository.findById(filmCategoryDto.categoryId()).orElseThrow(()->new NoSuchElementException("Nu exista category cu idul dat"));
        filmCategory.setFilm(film);
        filmCategory.setCategory(category);
        filmCategory.setLastUpdate(filmCategoryDto.lastUpdate());
        FilmCategory createdFilmCategory =filmCategoryRepository.save(filmCategory);
        return createdFilmCategory;
    }

    public void deleteFilmCategory(int filmCategoryId){
        filmCategoryRepository.deleteById(filmCategoryId);
    }

    public FilmCategory updateFilmCategory(int filmCategoryId,FilmCategoryDto filmCategoryDto){
        FilmCategory foundFilmCategory=filmCategoryRepository.findById(filmCategoryId).orElseThrow(()->new NoSuchElementException("Nu este filmCategory cu indexul: "+filmCategoryId));
        Film film =filmRepository.findById(filmCategoryDto.filmId()).orElseThrow(()->new NoSuchElementException("Nu exista film cu idul dat"));
        Category category =categoryRepository.findById(filmCategoryDto.categoryId()).orElseThrow(()->new NoSuchElementException("Nu exista category cu idul dat"));
        foundFilmCategory.setFilm(film);
        foundFilmCategory.setCategory(category);
        foundFilmCategory.setLastUpdate(filmCategoryDto.lastUpdate());
        filmCategoryRepository.save(foundFilmCategory);
        return foundFilmCategory;
    }

    public List<FilmCategory> getAllFilmCategories(){
        List<FilmCategory> filmCategories=filmCategoryRepository.findAll();
        return filmCategories;
    }

    public FilmCategory getFilmCategoryById(int filmCategoryId){
        FilmCategory filmCategory=filmCategoryRepository.findById(filmCategoryId).orElseThrow(()->new NoSuchElementException("Nu exista filmCategory cu id: "+filmCategoryId));
        return filmCategory;
    }
}
