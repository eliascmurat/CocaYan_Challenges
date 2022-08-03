package br.com.cocayan.swapi.entities.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cocayan.swapi.entities.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePeopleDto {

    @NotNull(message = "Nome da pessoa não pode ser nulo!")
    @Size(min = 2, max = 100, message = "Nome da pessoa deve ter entre 2 a 100 caracteres")
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
