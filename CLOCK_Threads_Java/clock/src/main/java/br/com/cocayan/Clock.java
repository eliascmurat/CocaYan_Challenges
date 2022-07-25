package br.com.cocayan;

public class Clock implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
