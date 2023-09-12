package com.example.movieservice.service;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Category;
import com.example.movieservice.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor addActor(Actor actor){
        Actor createdActor=actorRepository.save(actor);
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
