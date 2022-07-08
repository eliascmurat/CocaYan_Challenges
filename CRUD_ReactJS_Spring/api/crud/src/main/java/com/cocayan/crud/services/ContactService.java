package com.cocayan.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocayan.crud.entities.Contact;
import com.cocayan.crud.repositories.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;

    public Optional<Contact> getContactById(Long contactId) {
        return contactRepository.findById(contactId);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long contactId, Contact contact) {
        Optional<Contact> optional = getContactById(contactId);
        if (optional.isPresent()) {
            Contact updatedContact = optional.get();
            updatedContact.setPhoneNumber(contact.getPhoneNumber());

            return contactRepository.save(updatedContact);
        }

        return contact;
    }

    public boolean deleteContact(Long contactId) {        
        Optional<Contact> optional = getContactById(contactId);
        if (optional.isPresent()) {
            contactRepository.deleteById(contactId);
            return true;
        }

        return false;
    }
}
