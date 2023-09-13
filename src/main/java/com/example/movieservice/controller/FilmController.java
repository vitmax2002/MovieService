package com.example.movieservice.controller;

import com.example.movieservice.model.Film;
import com.example.movieservice.service.FilmService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    //private final Logger log= LoggerFactory.getLogger(FilmController.class);

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody Film film){
        Film createdFilm=filmService.addFilm(film);
        return ResponseEntity.ok().body(createdFilm);
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> removeFilm(@PathVariable int filmId){
        filmService.deleteFilm(filmId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Film> updateFilm(@PathVariable int filmId,@Valid @RequestBody Film film){
        Film updatedFilm=filmService.updateFilm(filmId,film);
        return ResponseEntity.ok().body(updatedFilm);
    }

    @GetMapping
      public ResponseEntity<List<Film>> getAllFilms(){
        List<Film> films=filmService.getAllFilms();
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("/{filmId}")
      public ResponseEntity<Film> getFilmById(@PathVariable int filmId){
        Film film=filmService.getFilmById(filmId);
        return ResponseEntity.ok().body(film);
    }
}
