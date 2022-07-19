package br.com.cocayan.swapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "hairPeople")
@Entity
public class HairPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hairPeopleId;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "peopleId")
    private People people;
}
