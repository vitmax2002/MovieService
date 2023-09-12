package com.example.movieservice.controller;

import com.example.movieservice.model.Actor;
import com.example.movieservice.model.Category;
import com.example.movieservice.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor){
        Actor createdActor= actorService.addActor(actor);
        return ResponseEntity.created(URI.create("/api/v1/actors")).body(createdActor);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Void> removeActor(@PathVariable int actorId){
        actorService.deleteActor(actorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<Actor> updateActor(@PathVariable int actorId, @RequestBody Actor actor){
        Actor updatedActor=actorService.updateActor(actorId,actor);
        return ResponseEntity.ok().body(updatedActor);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors(){
        List<Actor> actors=actorService.getAllActors();
        return ResponseEntity.ok().body(actors);
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable int actorId){
        Actor actor=actorService.getActorById(actorId);
        return ResponseEntity.ok().body(actor);
    }
}
