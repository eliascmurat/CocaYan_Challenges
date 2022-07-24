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
public class UpdatePeopleDto {
    
    @NotNull
    private Long peopleId;
    
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    private float height;

    private float mass;

    private Long birthYearId;
    
    private Long genderId;

    public People updatePeopleDtoToPeople(UpdatePeopleDto updatePeopleDto) {
        People people = new People();
        people.setPeopleId(updatePeopleDto.getPeopleId());
        people.setName(updatePeopleDto.getName());
        people.setHeight(updatePeopleDto.getHeight());
        people.setMass(updatePeopleDto.getMass());

        return people;
    }
}
