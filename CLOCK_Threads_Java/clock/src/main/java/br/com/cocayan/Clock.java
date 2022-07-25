package br.com.cocayan;

import java.util.ArrayList;
import java.util.List;

public class Clock implements Runnable {

    private List<Time> listLocalTime = new ArrayList<>();
    
    public Clock(List<Time> listLocalTime) {
        this.listLocalTime = listLocalTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Time time : listLocalTime) {    
                    if (Long.signum(time.getDifInMilis()) > 0) {               
                        Thread.sleep(time.getDifInMilis());
                        System.out.println(time.getMessage());
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
    
}
