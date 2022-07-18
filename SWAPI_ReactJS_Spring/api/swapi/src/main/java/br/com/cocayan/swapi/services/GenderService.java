package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cocayan.swapi.entities.Gender;
import br.com.cocayan.swapi.repositories.GenderRepository;

@Repository
public class GenderService {
    
    @Autowired
    GenderRepository genderRepository;

    public Optional<Gender> getGenderById(Long genderId) {
        return genderRepository.findById(genderId);
    } 

    public Gender createGender(Gender gender) {   
        return genderRepository.save(gender);
    } 

}
