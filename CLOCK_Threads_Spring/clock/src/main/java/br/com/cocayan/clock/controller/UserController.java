package br.com.cocayan.clock.controller;

import java.util.List;

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

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public String findUserById(@PathVariable Long userId) {
        try {
            return userService.findUserById(userId).toString();
        } catch (Exception e) {
            return "User não encontrado!";
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        try {
            return userService.updateUser(user).toString();
        } catch (Exception e) {
            return "userId não pode ser nulo!";
        }
    }

    @DeleteMapping
    public String deleteUser(@RequestBody User user) {
        try {
            String result = (userService.deleteUser(user.getUserId())) 
                ? "User deletado com sucesso!" 
                : "User não encontrado!";

            return result;
        } catch (Exception e) {
            return "userId não pode ser nulo!";
        }
    }

}
