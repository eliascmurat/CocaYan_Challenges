package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.People;
import br.com.cocayan.swapi.repositories.PeopleRepository;

@Service
public class PeopleService {
    
    @Autowired
    PeopleRepository peopleRepository;

    public Page<People> getAllPeoples(Pageable pageable) {
        return peopleRepository.findAll(pageable);
    } 

    public Optional<People> getPeopleById(Long peopleId) {
        return peopleRepository.findById(peopleId);
    } 

    public People createPeople(People people) {   
        return peopleRepository.save(people);
    } 

    public People updatePeople(People people) {
        Optional<People> optional = peopleRepository.findById(people.getPeopleId());
        if (optional.isPresent()) {
            People updatePeople = optional.get();
            updatePeople.setName(people.getName()); 
            updatePeople.setHeight(people.getHeight()); 
            updatePeople.setMass(people.getMass());
            updatePeople.setUpdated(people.getUpdated());
            updatePeople.setGender(people.getGender());

            return peopleRepository.save(updatePeople);
        }

        return people;
    }

    public boolean deletePeople(Long peopleId) {
        Optional<People> optional = peopleRepository.findById(peopleId);
        if (optional.isPresent()) {
            peopleRepository.deleteById(peopleId);
            
            return true;
        }
        
        return false;
    } 
}
