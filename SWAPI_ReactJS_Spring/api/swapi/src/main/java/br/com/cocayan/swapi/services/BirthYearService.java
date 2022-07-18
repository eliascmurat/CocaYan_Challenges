package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.BirthYear;
import br.com.cocayan.swapi.repositories.BirthYearRepository;

@Service
public class BirthYearService {
    
    @Autowired
    BirthYearRepository birthYearRepository;

    public Optional<BirthYear> getBirthYearById(Long birthYearId) {
        return birthYearRepository.findById(birthYearId);
    } 

    public BirthYear createBirthYear(BirthYear birthYear) {   
        return birthYearRepository.save(birthYear);
    } 
    
}
