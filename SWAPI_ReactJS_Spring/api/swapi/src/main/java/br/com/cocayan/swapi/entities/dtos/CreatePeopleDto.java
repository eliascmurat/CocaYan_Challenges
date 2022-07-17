package br.com.cocayan.swapi.entities.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CreatePeopleDto {

    @NotNull
    @Length(min = 2, max = 100)
    private String name;

    private float height;

    private float mass;

    public CreatePeopleDto(String name, float height, float mass) {
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

}
