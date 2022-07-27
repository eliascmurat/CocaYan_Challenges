package br.com.cocayan.clock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cocayan.clock.entities.User;
import br.com.cocayan.clock.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping()
    public void findAllUSers() { }

    @GetMapping("/{userId}")
    public void findUserById(@PathVariable Long userId) { }

    @PostMapping
    public void createUser(@RequestBody User user) { }

    @PutMapping
    public void updateUser(@RequestBody User user) { }

    @DeleteMapping
    public void deleteUser(@RequestBody User user) { }

}
