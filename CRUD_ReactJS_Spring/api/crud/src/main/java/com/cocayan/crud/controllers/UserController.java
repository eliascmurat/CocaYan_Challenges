package com.cocayan.crud.controllers;

import java.net.URI;
import java.util.List;
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
        return UserDto.listUserToListUserDto(users);
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
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {
        User newUser = userForm.userFormToUser(userForm);
        userService.createUser(newUser);
        
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getUserId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(newUser));
    }

    @PostMapping("/{userId}/contact")
    public ResponseEntity<ContactDto> addContact(@PathVariable Long userId, @RequestBody @Valid ContactForm contactForm, UriComponentsBuilder uriBuilder) {
        Optional<User> optional = userService.getUserById(userId);
        if (optional.isPresent()) {        
            Contact newContact = contactForm.contactFormToContact(contactForm);
            newContact.setUser(optional.get());
            contactService.createContact(newContact);
            
            URI uri = uriBuilder.path("/users/{id}/contact/{contactId}").buildAndExpand(optional.get().getUserId(), newContact.getContactId()).toUri();
            return ResponseEntity.created(uri).body(new ContactDto(newContact));
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    /*
    @PutMapping(path = {"/{id}"})
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User user) {
        Optional<User> optional = userService.getUserById(id);
        
        if (optional.isPresent()) {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<User> delete(@PathVariable Long id) {
        Optional<User> optional = userService.getUserById(id);
        
        if (optional.isPresent()) {
            if (userService.deleteUser(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */

}
