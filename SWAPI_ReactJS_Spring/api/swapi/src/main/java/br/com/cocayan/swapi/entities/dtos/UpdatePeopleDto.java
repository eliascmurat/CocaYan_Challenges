package br.com.cocayan.swapi.entities.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cocayan.swapi.entities.People;

import lombok.Data;

@Data
public class UpdatePeopleDto {
    
    @NotNull
    private Long peopleId;
    
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    private float height;

    private float mass;

    private Long genderId;

    public UpdatePeopleDto(Long peopleId, String name, float height, float mass) {
        this.peopleId = peopleId;
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public People updatePeopleDtoToPeople(UpdatePeopleDto updatePeopleDto) {
        return new People(
            updatePeopleDto.getPeopleId(),
            updatePeopleDto.getName(), 
            updatePeopleDto.getHeight(), 
            updatePeopleDto.getMass()
        );
    }
}
