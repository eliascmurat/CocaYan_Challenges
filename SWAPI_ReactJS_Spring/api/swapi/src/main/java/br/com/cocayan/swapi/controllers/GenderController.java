package br.com.cocayan.swapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cocayan.swapi.entities.Gender;
import br.com.cocayan.swapi.services.GenderService;

@RestController
@RequestMapping("/gender")
public class GenderController {
    
    @Autowired
    GenderService genderService;
    
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
}
