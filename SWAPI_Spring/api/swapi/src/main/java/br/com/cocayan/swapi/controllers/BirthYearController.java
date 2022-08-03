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

import br.com.cocayan.swapi.entities.people.BirthYear;
import br.com.cocayan.swapi.services.BirthYearService;

@RestController
@RequestMapping("/birthYear")
public class BirthYearController {
    
    @Autowired
    BirthYearService birthYearService;
    
    @GetMapping
    public Page<BirthYear> getAllBirthYears(
        @PageableDefault(
            sort = "birthYearId", 
            direction = Direction.ASC, 
            page = 0, 
            size = 10
        ) Pageable pageable
    ) {
        return birthYearService.getAllBirthYears(pageable);
    }

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

    @PutMapping
    public ResponseEntity<BirthYear> updateBirthYear(@RequestBody BirthYear birthYear) {
        Optional<BirthYear> optional = birthYearService.getBirthYearById(birthYear.getBirthYearId());

        if (optional.isPresent()) {
            return ResponseEntity.ok(birthYearService.updateBirthYear(birthYear));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBirthYear(@RequestBody BirthYear birthYear) {
        Optional<BirthYear> optional = birthYearService.getBirthYearById(birthYear.getBirthYearId());

        if (optional.isPresent()) {
            if (birthYearService.deleteBirthYear(birthYear.getBirthYearId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
