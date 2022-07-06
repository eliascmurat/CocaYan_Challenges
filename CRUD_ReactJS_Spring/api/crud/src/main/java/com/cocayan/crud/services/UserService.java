package com.cocayan.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cocayan.crud.entities.Contact;
import com.cocayan.crud.entities.User;
import com.cocayan.crud.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(Pageable paginacao) {
        return userRepository.findAll(paginacao);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        Optional<User> optional = getUserById(userId);
        if (optional.isPresent()) {
            User updatedUser = optional.get();
            updatedUser.setUserName(user.getUserName());
            updatedUser.setUserAge(user.getUserAge());
            
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
