package br.com.cocayan.swapi.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
        Page<People> peoples = peopleService.getAllPeoples(pageable);
        return PeopleDto.pagePeopleToPagePeopleDto(peoples);
    }

    @GetMapping("/{peopleId}")
    public ResponseEntity<PeopleDto> getPeopleById(@PathVariable Long peopleId) {
        Optional<People> optional = peopleService.getPeopleById(peopleId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(new PeopleDto(optional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PeopleDto> createPeople(
        @RequestBody @Valid CreatePeopleDto createPeopleDto, 
        UriComponentsBuilder uriBuilder
    ) {
        People people = createPeopleDto.createPeopleDtoToPeople(createPeopleDto);
        peopleService.createPeople(people);

        URI uri = uriBuilder.path("people/{peopleId}").buildAndExpand(people.getPeopleId()).toUri();
        return ResponseEntity.created(uri).body(new PeopleDto(people));
    }

    @PutMapping
    public ResponseEntity<PeopleDto> updatePeople(@RequestBody @Valid UpdatePeopleDto updatePeopleDto) {
        Optional<People> optional = peopleService.getPeopleById(updatePeopleDto.getPeopleId());

        if (optional.isPresent()) {
            People people = updatePeopleDto.updatePeopleDtoToPeople(updatePeopleDto);
            return ResponseEntity.ok(new PeopleDto(peopleService.updatePeople(people)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePeople(@RequestBody PeopleDto peopleDto) {
        Optional<People> optional = peopleService.getPeopleById(peopleDto.getPeopleId());

        if (optional.isPresent()) {
            if (peopleService.deletePeople(peopleDto.getPeopleId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
