package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "film")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;
    @NotBlank(message = "You should give a title for movie")
    private String title;
    @NotNull(message = "You should give year")
    private Year year;

    @NotBlank(message = "you should give the length of movie")
    private int length;
    @NotNull(message = "Choose the rating of film LOW,MEDIUM or HIGH")
    @Enumerated(EnumType.STRING)
    private Rating rating;


    @JsonIgnore
    @OneToMany(mappedBy ="film",cascade = CascadeType.ALL)
    private List<FilmCategory> filmCategories;

    @OneToMany(mappedBy = "film",cascade = CascadeType.ALL)
    private List<Language> languages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id",referencedColumnName = "filmId"),
            inverseJoinColumns = @JoinColumn(name = "actor_id",referencedColumnName = "actorId")
    )
    private List<Actor> actors;

    public Film() {
    }

    public Film(String title, Year year, int length, Rating rating, List<FilmCategory> filmCategories, List<Language> languages, List<Actor> actors) {
        this.title = title;
        this.year = year;
        this.length = length;
        this.rating = rating;
        this.filmCategories = filmCategories;
        this.languages = languages;
        this.actors = actors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(List<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId == film.filmId && length == film.length && Objects.equals(title, film.title) && Objects.equals(year, film.year) && rating == film.rating && Objects.equals(filmCategories, film.filmCategories) && Objects.equals(languages, film.languages) && Objects.equals(actors, film.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, year, length, rating, filmCategories, languages, actors);
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", length=" + length +
                ", rating=" + rating +
                ", filmCategories=" + filmCategories +
                ", languages=" + languages +
                ", actors=" + actors +
                '}';
    }
}
