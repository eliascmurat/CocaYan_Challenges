package br.com.cocayan.swapi.controllers;

import java.net.URI;
import java.util.Optional;

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

import br.com.cocayan.swapi.entities.people.Gender;
import br.com.cocayan.swapi.services.GenderService;

@RestController
@RequestMapping("/gender")
public class GenderController {
    
    @Autowired
    GenderService genderService;
    
    @GetMapping
    public Page<Gender> getAllGenders(
        @PageableDefault(
            sort = "genderId", 
            direction = Direction.ASC, 
            page = 0, 
            size = 10
        ) Pageable pageable
    ) {
        return genderService.getAllGenders(pageable);
    }
    
    @GetMapping("/{genderId}")
    public ResponseEntity<Gender> getGenderById(@PathVariable Long genderId) {
        Optional<Gender> optional = genderService.getGenderById(genderId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Gender> createGender(
        @RequestBody Gender gender, 
        UriComponentsBuilder uriBuilder
    ) {
        Gender createdGender = genderService.createGender(gender);

        URI uri = uriBuilder.path("gender/{genderId}").buildAndExpand(createdGender.getGenderId()).toUri();
        return ResponseEntity.created(uri).body(createdGender);
    }

    @PutMapping
    public ResponseEntity<Gender> updateGender(@RequestBody Gender gender) {
        Optional<Gender> optional = genderService.getGenderById(gender.getGenderId());

        if (optional.isPresent()) {
            return ResponseEntity.ok(genderService.updateGender(gender));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePeople(@RequestBody Gender gender) {
        Optional<Gender> optional = genderService.getGenderById(gender.getGenderId());

        if (optional.isPresent()) {
            if (genderService.deleteGender(gender.getGenderId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
