package br.com.cocayan.swapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "birthYear")
@Entity
public class BirthYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birthYearId")
    private Long birthYearId;

    @Column(name = "year")
    private String year;

    @Column(name = "age")
    private String age;

    @OneToOne(mappedBy = "birthYear")
    @JsonBackReference
    private People people;

}