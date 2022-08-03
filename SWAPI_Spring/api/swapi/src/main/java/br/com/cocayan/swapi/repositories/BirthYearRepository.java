package br.com.cocayan.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cocayan.swapi.entities.people.BirthYear;

@Repository
public interface BirthYearRepository extends JpaRepository<BirthYear, Long> { }
