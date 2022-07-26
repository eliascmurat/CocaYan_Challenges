package br.com.cocayan.clock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cocayan.clock.entities.User;

@Service
public class UserService {
    
    private List<User> users = new ArrayList<>();

    public List<User> findAllUsers() {
        return users;
    }

    public User findUserById(Long userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }

        return null;
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(User user) {
        for (User u : users) {
            if (u.getUserId() == user.getUserId()) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());

                return u;
            }
        }

        return null;
    }

    public boolean deleteUser(Long userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                users.remove(u);
                return true;
            }
        }

        return false;
    }

}
