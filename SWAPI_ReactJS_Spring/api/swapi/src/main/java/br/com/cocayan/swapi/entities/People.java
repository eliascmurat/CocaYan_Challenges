package br.com.cocayan.swapi.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class People {
    
    private Long peopleId;
    
    private String name;
    
    private float height;

    private float mass;

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date created = new Date();
    
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date updated = new Date();

    public People(String name, float height, float mass) {
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public People(Long peopleId, String name, float height, float mass) {
        this.peopleId = peopleId;
        this.name = name;
        this.height = height;
        this.mass = mass;
    }
    
}
