package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @SequenceGenerator(name ="sequence" ,sequenceName ="sequence" ,initialValue =1,allocationSize =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence")
    private int actorId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @ManyToMany(mappedBy = "actors",cascade = CascadeType.ALL)
    private List<Film> films;

    public Actor() {
    }

    public Actor(String firstName, String lastName, List<Film> films) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.films = films;
    }

    @JsonIgnore
    public List<Film> getFilms() {
        return films;
    }

    @JsonProperty
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return actorId == actor.actorId && Objects.equals(firstName, actor.firstName) && Objects.equals(lastName, actor.lastName) && Objects.equals(films, actor.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, firstName, lastName, films);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", films=" + films +
                '}';
    }
}
