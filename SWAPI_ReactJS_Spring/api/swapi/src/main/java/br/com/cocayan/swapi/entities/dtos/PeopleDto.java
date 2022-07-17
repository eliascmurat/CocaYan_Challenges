package br.com.cocayan.swapi.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cocayan.swapi.entities.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDto {

    private Long peopleId;

    private String name;
    
    private float height;
    
    private float mass;
                            
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss.SSSSSSX")
    private Date created;
    
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss.SSSSSSX")
    private Date updated;

    public static PeopleDto convertPeopleToPeopleDto(People people) {
        PeopleDto peopleDto = new PeopleDto();
        peopleDto.setPeopleId(people.getPeopleId());
        peopleDto.setName(people.getName());
        peopleDto.setHeight(people.getHeight());
        peopleDto.setMass(people.getMass());
        peopleDto.setCreated(people.getCreated());
        peopleDto.setUpdated(people.getUpdated());

        return peopleDto;
    }

}
