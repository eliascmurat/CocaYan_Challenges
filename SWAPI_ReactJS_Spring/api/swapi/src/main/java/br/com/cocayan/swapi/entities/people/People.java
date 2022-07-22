package br.com.cocayan.swapi.entities.people;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
@Entity
public class People {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peopleId")
    private Long peopleId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "height", precision = 10, scale = 2)
    private float height;

    @Column(name = "mass", precision = 10, scale = 2)
    private float mass;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<HairPeople> hairPeople = new ArrayList<>();

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<SkinPeople> skinPeople = new ArrayList<>();
    
    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<EyePeople> eyePeople = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "birthYearId")
    private BirthYear birthYear;

    @ManyToOne
    @JoinColumn(name = "genderId")
    private Gender gender;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();
    
    @Column(name = "updated")
    private LocalDateTime updated = LocalDateTime.now();

}
