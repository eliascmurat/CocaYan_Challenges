package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.people.Gender;
import br.com.cocayan.swapi.repositories.GenderRepository;

@Service
public class GenderService {
    
    @Autowired
    GenderRepository genderRepository;

    public Page<Gender> getAllGenders(Pageable pageable) {
        return genderRepository.findAll(pageable);
    } 

    public Optional<Gender> getGenderById(Long genderId) {
        return genderRepository.findById(genderId);
    } 

    public Gender createGender(Gender gender) {   
        return genderRepository.save(gender);
    } 

    public Gender updateGender(Gender gender) {
        Optional<Gender> optional = genderRepository.findById(gender.getGenderId());
        if (optional.isPresent()) {
            Gender updateGender = optional.get();
            updateGender.setName(gender.getName());

            return genderRepository.save(updateGender);
        }

        return gender;
    }

    public boolean deleteGender(Long genderId) {
        Optional<Gender> optional = genderRepository.findById(genderId);
        if (optional.isPresent()) {
            genderRepository.deleteById(genderId);
            
            return true;
        }
        
        return false;
    } 

}
