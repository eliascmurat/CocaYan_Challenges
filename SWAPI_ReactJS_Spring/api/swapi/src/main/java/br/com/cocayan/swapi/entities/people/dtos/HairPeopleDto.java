package br.com.cocayan.swapi.entities.people.dtos;

import br.com.cocayan.swapi.entities.color.Color;
import br.com.cocayan.swapi.entities.people.HairPeople;
import br.com.cocayan.swapi.entities.people.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HairPeopleDto {
    
    private Long peopleId;
    
    private Long colorId;

    public HairPeopleDto(HairPeople hairPeople) {
        this.peopleId = hairPeople.getPeople().getPeopleId();
        this.colorId = hairPeople.getColor().getColorId();
    }

    public HairPeople hairPeopleDtoToHairPeople(Color color, People people) {
        HairPeople hairPeople = new HairPeople();
        hairPeople.setColor(color);
        hairPeople.setPeople(people);

        return hairPeople;
    }

}
