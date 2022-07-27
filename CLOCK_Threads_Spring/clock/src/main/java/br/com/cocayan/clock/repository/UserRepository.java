package br.com.cocayan.clock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cocayan.clock.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
