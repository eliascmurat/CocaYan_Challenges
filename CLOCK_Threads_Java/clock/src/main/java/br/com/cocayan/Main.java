package br.com.cocayan;

/* 
 * @author Elias Murat
 * @sinze  2022-07-25
 */
public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock();
        Thread thread = new Thread(clock);

        thread.start();
    }
}
