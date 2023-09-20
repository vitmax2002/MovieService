package com.example.movieservice.service;

import com.example.movieservice.model.Film;
import com.example.movieservice.model.FilmCategory;
import com.example.movieservice.model.Language;
import com.example.movieservice.repository.FilmRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film addFilm(Film film){
        Film createdFilm=filmRepository.save(film);
        return createdFilm;
    }

    public void deleteFilm(int filmId){
        filmRepository.deleteById(filmId);
    }

    public Film updateFilm(int filmId,Film film){
        Film foundFilm=filmRepository.findById(filmId).orElseThrow(()->new NoSuchElementException("Nu este film cu indexul: "+filmId));
        foundFilm.setTitle(film.getTitle());
        foundFilm.setYear(film.getYear());
        foundFilm.setLength(film.getLength());
        foundFilm.setRating(film.getRating());
        foundFilm.setPlaces(film.getPlaces());
        foundFilm.setLanguages(film.getLanguages());
        filmRepository.save(foundFilm);
        return foundFilm;
    }

    public List<Film> getAllFilms(){
        List<Film> films=filmRepository.findAll();
        return films;
    }

    public Film getFilmById(int filmId){
        Film film=filmRepository.findById(filmId).orElseThrow(()->new NoSuchElementException("Nu exista film cu id: "+filmId));
        return film;
    }
}
