package br.com.cocayan.swapi.entities.people.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cocayan.swapi.entities.people.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePeopleDto {

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    private float height;

    private float mass;

    public People createPeopleDtoToPeople(CreatePeopleDto createPeopleDto) {
        People people = new People();
        people.setName(createPeopleDto.getName());
        people.setHeight(createPeopleDto.getHeight());
        people.setMass(createPeopleDto.getMass());

        return people;
    }
}
