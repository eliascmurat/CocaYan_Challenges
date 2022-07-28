package br.com.cocayan.clock.controller;

import java.net.URI;
import java.util.Optional;

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

import br.com.cocayan.clock.entities.User;
import br.com.cocayan.clock.service.ClockService;
import br.com.cocayan.clock.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    ClockService clockService;

    @GetMapping()
    public void getAllUsers() { 
        System.out.println(clockService.getUsers());
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> optional = userService.getUserById(userId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(
        @RequestBody User user, 
        UriComponentsBuilder uriBuilder
    ) {
        userService.createUser(user);

        URI uri = uriBuilder.path("user/{userId}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updatePeople(@RequestBody User user) {
        Optional<User> optional = userService.getUserById(user.getUserId());        
        
        if (optional.isPresent()) {
            return ResponseEntity.ok(userService.updateUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Optional<User> optional = userService.getUserById(user.getUserId());

        if (optional.isPresent()) {
            if (userService.deleteUser(user.getUserId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
