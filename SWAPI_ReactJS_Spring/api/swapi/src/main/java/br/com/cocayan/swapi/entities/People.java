package br.com.cocayan.swapi.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private Long peopleId;
    private String name;
    private float height;
    private float mass;
    private Date created;
    private Date updated;
}
