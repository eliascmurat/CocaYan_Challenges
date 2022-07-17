package br.com.cocayan.swapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "people")
@Entity
public class People {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peopleid")
    private Long peopleId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "height")
    private float height;

    @Column(name = "mass")
    private float mass;

    @Column(name = "created")
    private LocalDateTime created;
    
    @Column(name = "updated")
    private LocalDateTime updated;

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
