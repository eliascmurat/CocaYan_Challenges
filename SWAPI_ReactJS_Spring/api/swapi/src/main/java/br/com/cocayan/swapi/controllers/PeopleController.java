package br.com.cocayan.swapi.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cocayan.swapi.entities.People;

@RestController
@RequestMapping("/people")
public class PeopleController {
    
    @GetMapping
    public String getAllPeople() {
        return "chamando get all people";
    }

    @GetMapping("/{peopleId}")
    public String getPeopleById(@PathVariable Long peopleId) {
        return "chamando get people by id: " + peopleId;
    }

    @PostMapping
    public String createPeople(@RequestBody People people) {
        return "criando people:\n" + people.toString();
    }

    @PutMapping
    public String updatePeople(@RequestBody People people) {
        return "atualizando people pelo id: " + people.getPeopleId() + "\n" + people.toString();
    }

    @DeleteMapping
    public String deletePeople(@RequestBody People people) {
        return "deletando people pelo id: " + people.getPeopleId();
    }

}
