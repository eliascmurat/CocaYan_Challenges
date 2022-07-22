package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.people.BirthYear;
import br.com.cocayan.swapi.repositories.BirthYearRepository;

@Service
public class BirthYearService {
    
    @Autowired
    BirthYearRepository birthYearRepository;

    public Page<BirthYear> getAllBirthYears(Pageable pageable) {
        return birthYearRepository.findAll(pageable);
    }

    public Optional<BirthYear> getBirthYearById(Long birthYearId) {
        return birthYearRepository.findById(birthYearId);
    } 

    public BirthYear createBirthYear(BirthYear birthYear) {   
        return birthYearRepository.save(birthYear);
    } 

    public BirthYear updateBirthYear(BirthYear birthYear) {
        Optional<BirthYear> optional = birthYearRepository.findById(birthYear.getBirthYearId());
        if (optional.isPresent()) {
            BirthYear updateBirthYear = optional.get();
            updateBirthYear.setYear(birthYear.getYear());
            updateBirthYear.setAge(birthYear.getAge());

            return birthYearRepository.save(updateBirthYear);
        }

        return birthYear;
    }
    
    public boolean deleteBirthYear(Long birthYearId) {
        Optional<BirthYear> optional = birthYearRepository.findById(birthYearId);
        if (optional.isPresent()) {
            birthYearRepository.deleteById(birthYearId);
            
            return true;
        }
        
        return false;
    } 

}
