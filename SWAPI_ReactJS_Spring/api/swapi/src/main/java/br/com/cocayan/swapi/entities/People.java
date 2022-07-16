package br.com.cocayan.swapi.entities;

import java.util.Date;

import lombok.Data;

@Data
public class People {
    private Long peopleId;
    private String name;
    private float height;
    private float mass;
    private Date created;
    private Date updated;

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
