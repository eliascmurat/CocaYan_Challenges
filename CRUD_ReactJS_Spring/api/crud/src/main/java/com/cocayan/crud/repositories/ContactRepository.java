package com.cocayan.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cocayan.crud.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> { }
