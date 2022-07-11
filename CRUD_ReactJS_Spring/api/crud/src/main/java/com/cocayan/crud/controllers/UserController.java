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

    @PostMapping("/contact")
    public ResponseEntity<ContactDto> addContact(
        @RequestBody @Valid ContactForm contactForm, 
        UriComponentsBuilder uriBuilder
    ) {
        Optional<User> optional = userService.getUserById(contactForm.getUserId());

        if (optional.isPresent()) {        
            Contact newContact = contactForm.contactFormToContact(contactForm);
            newContact.setUser(optional.get());
            contactService.createContact(newContact);
            
            URI uri = uriBuilder.path("/users/{id}").buildAndExpand(optional.get().getUserId()).toUri();
            return ResponseEntity.created(uri).body(new ContactDto(newContact));
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserForm userForm) {
        Optional<User> optional = userService.getUserById(userForm.getUserId());
        
        if (optional.isPresent()) {
            User updatedUser = userForm.userFormToUser(userForm);
            return ResponseEntity.ok(new UserDto(userService.updateUser(userForm.getUserId(), updatedUser)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PutMapping("/contact")
    public ResponseEntity<ContactDto> updateContact(@RequestBody @Valid ContactForm contactForm) {
        Optional<User> userOptional = userService.getUserById(contactForm.getUserId());

        if (userOptional.isPresent()) {     
            if (userOptional.get().hasContact(contactForm.getContactId())) {
                Contact updatedContact = contactForm.contactFormToContact(contactForm);
                return ResponseEntity.ok(new ContactDto(contactService.updateContact(contactForm.getContactId(), updatedContact)));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestBody UserForm userForm) {
        Optional<User> optional = userService.getUserById(userForm.getUserId());
        
        if (optional.isPresent()) {
            if (userService.deleteUser(userForm.getUserId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/contact")
    public ResponseEntity<?> deleteContact(@RequestBody ContactForm contactForm) {
        Optional<User> userOptional = userService.getUserById(contactForm.getUserId());
        
        if (userOptional.isPresent()) {
            if (userOptional.get().hasContact(contactForm.getContactId())) {
                Optional<Contact> contact = contactService.getContactById(contactForm.getContactId());

                userOptional.get().getContactList().remove(contact.get());
                userService.updateUser(contactForm.getUserId(), userOptional.get());

                if (contactService.deleteContact(contactForm.getContactId())) {    
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
