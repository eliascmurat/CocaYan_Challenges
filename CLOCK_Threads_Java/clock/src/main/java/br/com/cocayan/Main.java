package br.com.cocayan;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/* 
 * @author Elias Murat
 * @sinze  2022-07-25
 */
public class Main {
    public static void main(String[] args) {
        // Lista que vai ser passada como param no construtor da Class Clock
        List<LocalTime> times = new ArrayList<>();

        // Add tempo do "alarme"
        times.add(LocalTime.parse("11:45:00"));
        times.add(LocalTime.parse("11:46:00"));

        // Cria Thread passando a classe Clock com implements Runnable como param e
        // na classe Clock passando a lista de horarios
        new Thread(new Clock(times)).start();
    }
}
