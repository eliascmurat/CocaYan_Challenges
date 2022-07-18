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

import br.com.cocayan.swapi.entities.BirthYear;
import br.com.cocayan.swapi.services.BirthYearService;

@RestController
@RequestMapping("/birthYear")
public class BirthYearController {
    
    @Autowired
    BirthYearService birthYearService;
    
    @GetMapping("/{birthYearId}")
    public ResponseEntity<BirthYear> getBirthYearById(@PathVariable Long birthYearId) {
        Optional<BirthYear> optional = birthYearService.getBirthYearById(birthYearId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<BirthYear> createBirthYear(
        @RequestBody BirthYear birthYear, 
        UriComponentsBuilder uriBuilder
    ) {
        BirthYear createdBirthYear = birthYearService.createBirthYear(birthYear);

        URI uri = uriBuilder.path("birthYear/{birthYearId}").buildAndExpand(createdBirthYear.getBirthYearId()).toUri();
        return ResponseEntity.created(uri).body(createdBirthYear);
    }
}
