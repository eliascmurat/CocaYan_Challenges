package br.com.cocayan.swapi.entities.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

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
                            
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime created;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime updated;

    private String gender;

    public PeopleDto(People people) {
        this.peopleId = people.getPeopleId();
        this.name = people.getName();
        this.height = people.getHeight();
        this.mass = people.getMass();
        this.created = people.getCreated();
        this.updated = people.getUpdated();
        this.gender = people.getGender().getName();
    }

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

    public static Page<PeopleDto> pagePeopleToPagePeopleDto(Page<People> peoples) {
        return peoples.map(PeopleDto::new);
    }

}
