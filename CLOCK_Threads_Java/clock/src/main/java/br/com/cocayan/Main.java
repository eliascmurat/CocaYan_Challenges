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
        List<Time> times = new ArrayList<>();

        // Trocar horario aqui
        times.add(new Time("Boa dia", LocalTime.parse("15:05:00")));
        times.add(new Time("Boa tarde", LocalTime.parse("15:06:00")));
        times.add(new Time("Boa noite", LocalTime.parse("15:07:00")));

        new Thread(new Clock(times)).start();
    }
}