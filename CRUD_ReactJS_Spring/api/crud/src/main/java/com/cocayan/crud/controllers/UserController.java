package com.cocayan.crud.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cocayan.crud.entities.User;
import com.cocayan.crud.services.UserService;

@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> optional = userService.getUserById(id);
        
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        User newUser = userService.createUser(user);
        
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getUserId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

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

}
