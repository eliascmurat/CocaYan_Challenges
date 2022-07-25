package br.com.cocayan;

import java.time.Duration;
import java.time.LocalTime;

public class Time {
    private String message;
    private LocalTime time;

    public Time() { }

    public Time(String message, LocalTime time) { 
        this.message = message;
        this.time = time;
    }

    public Long getDifInMilis() {
        return Duration.between(LocalTime.now(), this.time).toMillis();
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time {message:" + message + ", time:" + time + "}";
    }

}
