package com.example.movieservice.service;


import com.example.movieservice.model.Film;
import com.example.movieservice.model.Language;
import com.example.movieservice.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language addLanguage(Language language){
        Language createdLanguage= languageRepository.save(language);
        return createdLanguage;
    }

    public void deleteLanguage(int languageId){
        languageRepository.deleteById(languageId);
    }

    public Language updateLanguage(int languageId, Language language){
        Language foundLanguage=languageRepository.findById(languageId).orElseThrow(()->new NoSuchElementException("Nu este language cu indexul: "+languageId));
        foundLanguage.setName(language.getName());
        foundLanguage.setLastUpdate(language.getLastUpdate());
        foundLanguage.setFilm(language.getFilm());
        languageRepository.save(foundLanguage);
        return foundLanguage;
    }

    public List<Language> getAllLanguages(){
        List<Language> languages=languageRepository.findAll();
        return languages;
    }

    public Language getLanguageById(int languageId){
        Language language=languageRepository.findById(languageId).orElseThrow(()->new NoSuchElementException("Nu exista language cu id: "+languageId));
        return language;
    }
}
