package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "language")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;
    private String name;
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "film")
    private Film film;

    public Language() {
    }

    public Language(String name, LocalDateTime lastUpdate, Film film) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.film = film;
    }

    @JsonIgnore
    public Film getFilm() {
        return film;
    }

    @JsonProperty
    public void setFilm(Film film) {
        this.film = film;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return languageId == language.languageId && Objects.equals(name, language.name) && Objects.equals(lastUpdate, language.lastUpdate) && Objects.equals(film, language.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, name, lastUpdate, film);
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", film=" + film +
                '}';
    }
}
