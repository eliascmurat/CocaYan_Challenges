package br.com.cocayan;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Clock implements Runnable {

    // Lista de horarios
    private List<LocalTime> listLocalTime = new ArrayList<>();
    
    // Horario atual
    private LocalTime now = LocalTime.now();

    // Construtor do Clock
    public Clock(List<LocalTime> listLocalTime) {
        this.listLocalTime = listLocalTime;
    }

    // Função para pegar a diferença entre os horarios
    private Long getDifInMilis(LocalTime t1, LocalTime t2) {
        return Duration.between(t1, t2).toMillis();
    }

    // Run
    @Override
    public void run() {
        try {
            // Percorre a lista de tempos passado pelo construtor
            for (LocalTime localTime : this.listLocalTime) {

                //Chama função para pegar a diferença entre os horarios
                Long dif = getDifInMilis(this.now, localTime);

                System.out.println("Time atual = " + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());
                System.out.println("A mimir...");
                
                // Bota a Thread para nanar :)
                Thread.sleep(dif);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
