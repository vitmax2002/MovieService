package com.example.movieservice.service;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Film;
import com.example.movieservice.model.Language;
import com.example.movieservice.model.dto.FilmDto;
import com.example.movieservice.repository.ActorRepository;
import com.example.movieservice.repository.FilmRepository;
import com.example.movieservice.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final ActorRepository actorRepository;

    public FilmService(FilmRepository filmRepository, LanguageRepository languageRepository, ActorRepository actorRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
        this.actorRepository = actorRepository;
    }

    public Film addFilm(FilmDto filmDto){
        Film film=new Film();
        film.setTitle(filmDto.title());
        film.setYear(filmDto.year());
        film.setLength(filmDto.length());
        film.setPlaces(filmDto.places());
        film.setRating(filmDto.rating());
        for(String name:filmDto.languages()){
            if(languageRepository.existsByName(name)){
                Language language=languageRepository.findByName(name);
                language.setFilm(film);
                film.getLanguages().add(language);
            }
        }
        for(String firstName:filmDto.actors()){
            if(actorRepository.existsByFirstName(firstName)){
                Actor actor=actorRepository.findByFirstName(firstName);
                film.getActors().add(actor);
            }
        }
        Film createdFilm=filmRepository.save(film);
        return createdFilm;
    }

    public void deleteFilm(int filmId){
        filmRepository.deleteById(filmId);
    }

    public Film updateFilm(int filmId,FilmDto filmDto){
        Film foundFilm=filmRepository.findById(filmId).orElseThrow(()->new NoSuchElementException("Nu este film cu indexul: "+filmId));
        foundFilm.setTitle(filmDto.title());
        foundFilm.setYear(filmDto.year());
        foundFilm.setLength(filmDto.length());
        foundFilm.setRating(filmDto.rating());
        List<Language> languages=new ArrayList<>();
        foundFilm.getLanguages().forEach(language -> language.setFilm(null));
        for(String name:filmDto.languages()){
            if(languageRepository.existsByName(name)){
                Language language=languageRepository.findByName(name);
                language.setFilm(foundFilm);
                languages.add(language);
            }
        }
        foundFilm.setLanguages(languages);
        List<Actor> actors=new ArrayList<>();
        for(String firstName:filmDto.actors()){
            if(actorRepository.existsByFirstName(firstName)){
                Actor actor=actorRepository.findByFirstName(firstName);
                actors.add(actor);
            }
        }
        foundFilm.setActors(actors);
        filmRepository.save(foundFilm);
        return foundFilm;
    }

    public Film changeNumberOfPlaces(int filmId){
        Film film= filmRepository.findById(filmId).orElseThrow(()->new NoSuchElementException("Nu exista film cu id: "+filmId));
        film.setPlaces(film.getPlaces()-1);
        filmRepository.save(film);
        return film;
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
