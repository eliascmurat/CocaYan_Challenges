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

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    } 

    public ClockService(UserRepository userRepository) {
        this.userRepository = userRepository;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                if (
                    LocalTime.now().getHour() == 8 && 
                    LocalTime.now().getMinute() == 30 &&
                    LocalTime.now().getSecond() == 0
                ) {    
                    this.users = this.getAllUsers();            
                    System.out.println("\n\n" + this.users + "\n\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
