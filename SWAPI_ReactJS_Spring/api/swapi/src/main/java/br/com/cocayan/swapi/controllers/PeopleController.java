package br.com.cocayan.swapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public Page<PeopleDto> getAllPeople(
        @PageableDefault(
            sort = "peopleId", 
            direction = Direction.ASC, 
            page = 0, 
            size = 10
        ) Pageable pageable
    ) {
        Page<People> peoples = peopleService.getAllPeople(pageable);
        return PeopleDto.pagePeopleToPagePeopleDto(peoples);
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
    public PeopleDto createPeople(@RequestBody @Valid CreatePeopleDto createPeopleDto) {
        People people = new People(
            createPeopleDto.getName(), 
            createPeopleDto.getHeight(), 
            createPeopleDto.getMass()
        );

        return PeopleDto.convertPeopleToPeopleDto(peopleService.createPeople(people));
    }

    @PutMapping
    public String updatePeople(@RequestBody @Valid UpdatePeopleDto updatePeopleDto) {
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
