package br.com.cocayan.swapi.entities.people;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.cocayan.swapi.entities.color.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hairPeople")
@Entity
@IdClass(HairPeople.class)
public class HairPeople implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorId", referencedColumnName = "colorId")
    @JsonBackReference
    private Color color;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peopleId", referencedColumnName = "peopleId")
    @JsonBackReference
    private People people;

}
