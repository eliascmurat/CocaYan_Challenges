package br.com.cocayan.swapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "people")
@Entity
public class People {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peopleId")
    private Long peopleId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "height")
    private float height;

    @Column(name = "mass")
    private float mass;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();
    
    @Column(name = "updated")
    private LocalDateTime updated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "genderId")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "birthYearId")
    private BirthYear birthYear;

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
