package com.cocayan.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocayan.crud.entities.User;
import com.cocayan.crud.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long idUser) {
        return userRepository.findById(idUser);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        Optional<User> optional = getUserById(userId);
        if (optional.isPresent()) {
            User updatedUser = optional.get();
            updatedUser.setName(user.getName());
            updatedUser.setAge(user.getAge());
            
            return userRepository.save(updatedUser);
        }

        return user;
    }

    public boolean deleteUser(Long userId) {
        Optional<User> optional = getUserById(userId);
        if (optional.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}
