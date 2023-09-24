package com.example.movieservice.service;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Film;
import com.example.movieservice.repository.ActorRepository;
import com.example.movieservice.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;

    public ActorService(ActorRepository actorRepository, FilmRepository filmRepository) {
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
    }

    public Actor addActor(Actor actor){
        Actor createdActor=actorRepository.save(actor);
        if(createdActor.getFilms()!=null) {
            List<Film> films = createdActor.getFilms();
            for (Film film : films) {
                List<Actor> actors = film.getActors();
                actors.add(actor);
                film.setActors(actors);
                filmRepository.save(film);
            }
        }
        return createdActor;
    }

    public void deleteActor(int actorId){
        actorRepository.deleteById(actorId);
    }

    public Actor updateActor(int actorId, Actor actor){
        Actor foundActor=actorRepository.findById(actorId).orElseThrow(()->new NoSuchElementException("Nu este actor cu indexul: "+actorId));
        foundActor.setFirstName(actor.getFirstName());
        foundActor.setLastName(actor.getLastName());
        foundActor.setFilms(actor.getFilms());
        actorRepository.save(foundActor);
        return foundActor;
    }

    public List<Actor> getAllActors(){
        List<Actor> actors=actorRepository.findAll();
        return actors;
    }

    public Actor getActorById(int actorId){
        Actor actor=actorRepository.findById(actorId).orElseThrow(()->new NoSuchElementException("Nu exista actor cu id: "+actorId));
        return actor;
    }
}
