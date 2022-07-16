package br.com.cocayan.swapi.controllers;

import java.util.ArrayList;
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
import br.com.cocayan.swapi.entities.dtos.CreatePeopleDto;
import br.com.cocayan.swapi.entities.dtos.PeopleDto;
import br.com.cocayan.swapi.entities.dtos.UpdatePeopleDto;
import br.com.cocayan.swapi.services.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping
    public List<PeopleDto> getAllPeople() {
        List<PeopleDto> peopleDtoList = new ArrayList<>();
        for (People people : peopleService.getAllPeople()) {
            try {
                peopleDtoList.add(PeopleDto.convertPeopleToPeopleDto(people));
            } catch (Exception e) {
                System.err.println(e);
                return peopleDtoList;
            }
        }

        return peopleDtoList;
    }

    @GetMapping("/{peopleId}")
    public String getPeopleById(@PathVariable Long peopleId) {
        try {
            PeopleDto peopleDto = PeopleDto.convertPeopleToPeopleDto(peopleService.getPeopleById(peopleId));
            
            if (peopleDto != null) {
                return peopleDto.toString();
            } else {
                return "People com o id: " + peopleId + ", não foi encontrado...";
            }
        } catch (Exception e) {
            System.err.println(e);
            return "People com o id: " + peopleId + ", não foi encontrado...";
        }
    }

    @PostMapping
    public PeopleDto createPeople(@RequestBody CreatePeopleDto createPeopleDto) {
        People people = new People(
            createPeopleDto.getName(), 
            createPeopleDto.getHeight(), 
            createPeopleDto.getMass()
        );

        return PeopleDto.convertPeopleToPeopleDto(peopleService.createPeople(people));
    }

    @PutMapping
    public String updatePeople(@RequestBody UpdatePeopleDto updatePeopleDto) {
        People people = new People(
            updatePeopleDto.getPeopleId(), 
            updatePeopleDto.getName(), 
            updatePeopleDto.getHeight(), 
            updatePeopleDto.getMass()
        );

        PeopleDto peopleDto = PeopleDto.convertPeopleToPeopleDto(peopleService.updatePeople(people));

        if (peopleDto != null) {
            return peopleDto.toString();
        } else {
            return "People com o id: " + updatePeopleDto.getPeopleId() + ", não foi encontrado...";
        }    
    }

    @DeleteMapping
    public String deletePeople(@RequestBody PeopleDto peopleDto) {
        if (peopleService.deletePeople(peopleDto.getPeopleId())) {
            return "People com o id: " + peopleDto.getPeopleId() + ", deletado com sucesso!";
        } else {
            return "People com o id: " + peopleDto.getPeopleId() + ", não foi encontrado...";
        }
    }

}
