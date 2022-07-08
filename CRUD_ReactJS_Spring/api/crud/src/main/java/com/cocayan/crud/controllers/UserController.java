package com.cocayan.crud.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

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

import com.cocayan.crud.entities.Contact;
import com.cocayan.crud.entities.User;
import com.cocayan.crud.services.ContactService;
import com.cocayan.crud.services.UserService;
import com.cocayan.crud.services.dtos.ContactDto;
import com.cocayan.crud.services.dtos.UserDto;
import com.cocayan.crud.services.form.ContactForm;
import com.cocayan.crud.services.form.UserForm;

@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public Page<UserDto> getAllUsers(
        @PageableDefault(
            sort = "userId", 
            direction = Direction.ASC, 
            page = 0, 
            size = 10
        ) Pageable pageable
    ) {
        Page<User> users = userService.getAllUsers(pageable);
        return UserDto.pageUserToPageUserDto(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        Optional<User> optional = userService.getUserById(id);
        
        if (optional.isPresent()) {
            return ResponseEntity.ok(new UserDto(optional.get()));
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
        @RequestBody @Valid UserForm userForm, 
        UriComponentsBuilder uriBuilder
    ) {
        User newUser = userForm.userFormToUser(userForm);
        userService.createUser(newUser);
        
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getUserId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(newUser));
    }

    @PostMapping("/{userId}/contact")
    public ResponseEntity<ContactDto> addContact(
        @PathVariable Long userId, 
        @RequestBody @Valid ContactForm contactForm, 
        UriComponentsBuilder uriBuilder
    ) {
        Optional<User> optional = userService.getUserById(userId);

        if (optional.isPresent()) {        
            Contact newContact = contactForm.contactFormToContact(contactForm);
            newContact.setUser(optional.get());
            contactService.createContact(newContact);
            
            URI uri = uriBuilder.path("/users/{userId}/contact/{contactId}").buildAndExpand(optional.get().getUserId(), newContact.getContactId()).toUri();
            return ResponseEntity.created(uri).body(new ContactDto(newContact));
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
        @PathVariable Long userId, 
        @RequestBody @Valid UserForm userForm
    ) {
        Optional<User> optional = userService.getUserById(userId);
        
        if (optional.isPresent()) {
            User updatedUser = userForm.userFormToUser(userForm);
            return ResponseEntity.ok(new UserDto(userService.updateUser(userId, updatedUser)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PutMapping("/{userId}/contact/{contactId}")
    public ResponseEntity<ContactDto> updateContact(
        @PathVariable Long userId, 
        @PathVariable Long contactId, 
        @RequestBody @Valid ContactForm contactForm
    ) {
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            boolean hasContact = userOptional.get().getContactList().stream().anyMatch(c -> contactId.equals(c.getContactId()));
            
            if (hasContact) {
                Contact updatedContact = contactForm.contactFormToContact(contactForm);
                return ResponseEntity.ok(new ContactDto(contactService.updateContact(contactId, updatedContact)));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Optional<User> optional = userService.getUserById(userId);
        
        if (optional.isPresent()) {
            if (userService.deleteUser(userId)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/contact/{contactId}")
    public ResponseEntity<?> deleteContact(
        @PathVariable Long userId, 
        @PathVariable Long contactId
    ) {
        Optional<User> userOptional = userService.getUserById(userId);
        
        if (userOptional.isPresent()) {
            if (userOptional.get().hasContact(contactId)) {
                Optional<Contact> contact = contactService.getContactById(contactId);

                userOptional.get().getContactList().remove(contact.get());
                userService.updateUser(userId, userOptional.get());

                if (contactService.deleteContact(contactId)) {    
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.noContent().build();
                }    
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
