package br.com.cocayan.swapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cocayan.swapi.entities.People;
import br.com.cocayan.swapi.services.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping
    public List<People> getAllPeople() {
        return peopleService.getAllPeople();
    }

    @GetMapping("/{peopleId}")
    public String getPeopleById(@PathVariable Long peopleId) {
        People people = peopleService.getPeopleById(peopleId);

        if (people != null) {
            return people.toString();
        } else {
            return "People com o id: " + peopleId + ", não foi encontrado...";
        }
    }

    @PostMapping
    public People createPeople(@RequestBody People people) {
        return peopleService.createPeople(people);
    }

    @PutMapping
    public String updatePeople(@RequestBody People people) {
        People peopleUpdated = peopleService.updatePeople(people);

        if (peopleUpdated != null) {
            return peopleUpdated.toString();
        } else {
            return "People com o id: " + people.getPeopleId() + ", não foi encontrado...";
        }    
    }

    @DeleteMapping
    public String deletePeople(@RequestBody People people) {
        if (peopleService.deletePeople(people.getPeopleId())) {
            return "People com o id: " + people.getPeopleId() + ", deletado com sucesso!";
        } else {
            return "People com o id: " + people.getPeopleId() + ", não foi encontrado...";
        }
    }

}
