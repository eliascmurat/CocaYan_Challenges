package br.com.cocayan.swapi.entities.dtos;

import lombok.Data;

@Data
public class UpdatePeopleDto {
    private Long peopleId;
    private String name;
    private float height;
    private float mass;

    public UpdatePeopleDto(Long peopleId, String name, float height, float mass) {
        this.peopleId = peopleId;
        this.name = name;
        this.height = height;
        this.mass = mass;
    }
}
