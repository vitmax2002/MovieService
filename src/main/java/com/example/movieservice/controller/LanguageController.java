package com.example.movieservice.controller;

import com.example.movieservice.model.Film;
import com.example.movieservice.model.Language;
import com.example.movieservice.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<Language> addLanguage(@RequestBody Language language){
        Language createdLanguage=languageService.addLanguage(language);
        return ResponseEntity.ok().body(createdLanguage);
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Void> removeLanguage(@PathVariable int languageId){
        languageService.deleteLanguage(languageId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<Language> updateLanguage(@PathVariable int languageId, @RequestBody Language language){
        Language updatedLanguage=languageService.updateLanguage(languageId,language);
        return ResponseEntity.ok().body(updatedLanguage);
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages(){
        List<Language> languages=languageService.getAllLanguages();
        return ResponseEntity.ok().body(languages);
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<Language> getFilmById(@PathVariable int languageId){
        Language language=languageService.getLanguageById(languageId);
        return ResponseEntity.ok().body(language);
    }
}
