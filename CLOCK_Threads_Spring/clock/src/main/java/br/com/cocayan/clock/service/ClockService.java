package br.com.cocayan.clock.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cocayan.clock.entities.User;
import br.com.cocayan.clock.repository.UserRepository;

@Service
public class ClockService implements Runnable {

    private List<User> users = new ArrayList<>();
    
    private UserRepository userRepository; 

    public ClockService(UserRepository userRepository) {
        this.userRepository = userRepository;

        new Thread(this).start();
    }

    public List<User> getUsers() {
        return users;
    }
    
    private List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                if (
                    LocalTime.now().getHour() == 18 && 
                    LocalTime.now().getMinute() == 42 &&
                    LocalTime.now().getSecond() == 0
                ) {    
                    this.users = this.getAllUsers();            
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
