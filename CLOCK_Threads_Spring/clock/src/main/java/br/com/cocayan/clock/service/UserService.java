package br.com.cocayan.clock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cocayan.clock.entities.User;
import br.com.cocayan.clock.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Optional<User> getPeopleById(Long peopleId) {
        return userRepository.findById(peopleId);
    } 

    public User createUser(User user) {   
        return userRepository.save(user);
    } 

    public User updateUser(User user) {
        Optional<User> optional = userRepository.findById(user.getUserId());
        if (optional.isPresent()) {
            User updateUser = optional.get();
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());

            return userRepository.save(updateUser);
        }

        return user;
    }

    public boolean deleteUser(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            userRepository.deleteById(userId);
            
            return true;
        }
        
        return false;
    } 

}
