package br.com.cocayan.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cocayan.swapi.entities.people.HairPeople;

@Repository
public interface HairPeopleRepository extends JpaRepository<HairPeople, Long>{ 
    // Não ta funfando
}
