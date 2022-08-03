package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.people.HairPeople;
import br.com.cocayan.swapi.repositories.HairPeopleRepository;

@Service
public class HairPeopleService {
    
    @Autowired
    HairPeopleRepository hairPeopleRepository;

    /*
     * TODO: 
     * 
     * Criar o getById: pelo people_id + color_id
     * Criar getAll: pelo people_id -> retorna as cores de cabelo de um pessoa
     * Criar getAll: pelo color_id -> retorna as pessoas que possui aquela cor de cabelo
     *  
     */ 


    public HairPeople createHairPeople(HairPeople hairPeople) {   
        return hairPeopleRepository.save(hairPeople); // Criar o "link"
    } 

    public HairPeople updateHairPeople(HairPeople hairPeople) {
        Optional<HairPeople> optional = hairPeopleRepository.findById(hairPeople.getPeople().getPeopleId());
        if (optional.isPresent()) {
            HairPeople updateHairPeople = optional.get();
            updateHairPeople.setColor(hairPeople.getColor()); // Só atualiza a color

            return hairPeopleRepository.save(updateHairPeople);
        }

        return hairPeople;
    }

    public boolean deleteHairPeople(Long peopleId) {
        // Trocar para get all
        Optional<HairPeople> optional = hairPeopleRepository.findById(peopleId);
        if (optional.isPresent()) {

            // Deletar por people_id + color_id
            // ou
            // Deletar todos pelo people_id
            hairPeopleRepository.deleteById(peopleId);
            
            return true;
        }
        
        return false;
    } 

}
