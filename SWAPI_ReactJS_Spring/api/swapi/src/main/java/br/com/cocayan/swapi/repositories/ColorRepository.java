package br.com.cocayan.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cocayan.swapi.entities.color.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> { }
