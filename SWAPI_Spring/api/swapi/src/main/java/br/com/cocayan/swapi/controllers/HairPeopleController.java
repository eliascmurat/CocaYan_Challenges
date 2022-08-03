package br.com.cocayan.swapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cocayan.swapi.services.HairPeopleService;

@RestController
@RequestMapping("/people/hair")
public class HairPeopleController {
    
    @Autowired
    HairPeopleService hairPeopleService;

    // Criar end-points

}
