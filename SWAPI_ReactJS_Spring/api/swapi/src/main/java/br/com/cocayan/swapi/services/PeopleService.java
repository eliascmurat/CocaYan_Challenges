package br.com.cocayan.swapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.People;

@Service
public class PeopleService {
    
    List<People> peoples = new ArrayList<>();

    public List<People> getAllPeople() {
        return peoples;
    } 

    public People getPeopleById(Long peopleId) {
        for (People p : peoples) {
            if (p.getPeopleId() == peopleId) {
                return p;
            }
        }

        return null;
    } 

    public People createPeople(People people) {
        people.setPeopleId((long) (peoples.size() + 1));
        peoples.add(people);        
        return people;
    } 

    public People updatePeople(People people) {
        for (People p : peoples) {
            if (p.getPeopleId() == people.getPeopleId()) {
                p.setName(people.getName());
                p.setHeight(people.getHeight());
                p.setMass(people.getMass());

                return p;
            }
        }

        return null;
    }

    public boolean deletePeople(Long peopleId) {
        for (People p : peoples) {
            if (p.getPeopleId() == peopleId) {
                peoples.remove(p);
                return true;
            }
        }

        return false;
    } 
}