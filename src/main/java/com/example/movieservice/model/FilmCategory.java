package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_category")
public class FilmCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmCategoryId;

    @NotNull(message ="Film can not be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_fk")
    private Film film;

    @NotNull(message ="Category can not be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_fk")
    private Category category;

    private LocalDateTime lastUpdate;

    public FilmCategory() {
    }

    public FilmCategory(Film film, Category category, LocalDateTime lastUpdate) {
        this.film = film;
        this.category = category;
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getFilmCategoryId() {
        return filmCategoryId;
    }

    public void setFilmCategoryId(int filmCategoryId) {
        this.filmCategoryId = filmCategoryId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategory that = (FilmCategory) o;
        return filmCategoryId == that.filmCategoryId && Objects.equals(film, that.film) && Objects.equals(category, that.category) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmCategoryId, film, category, lastUpdate);
    }

    @Override
    public String toString() {
        return "FilmCategory{" +
                "filmCategoryId=" + filmCategoryId +
                ", film=" + film +
                ", category=" + category +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
