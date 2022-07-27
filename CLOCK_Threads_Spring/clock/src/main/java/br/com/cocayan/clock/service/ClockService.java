package br.com.cocayan.clock.service;

import org.springframework.stereotype.Service;

@Service
public class ClockService implements Runnable {

    public ClockService() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("ping");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
