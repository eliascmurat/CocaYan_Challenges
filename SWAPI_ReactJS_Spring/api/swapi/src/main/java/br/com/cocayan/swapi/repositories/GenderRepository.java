package br.com.cocayan.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cocayan.swapi.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> { }
