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

    private String birthYear;

    private String hairPeople;

    public PeopleDto(People people) {
        this.peopleId = people.getPeopleId();
        this.name = people.getName();
        this.height = people.getHeight();
        this.mass = people.getMass();
        this.created = people.getCreated();
        this.updated = people.getUpdated();

        if (people.getGender() != null) {
            this.gender = people.getGender().getName();
        } else {
            this.gender = "n/a";
        }

        if (people.getBirthYear() != null) {
            this.birthYear = people.getBirthYear().getYear() + people.getBirthYear().getAge();
        } else {
            this.gender = "unknown";
        }

        if (people.getHairPeople().size() > 0) {            
            people.getHairPeople().forEach(item -> {
                this.hairPeople = item.getColor().getName();
            });
        } else {
            this.hairPeople = "unknown";
        }
    }

    public static Page<PeopleDto> pagePeopleToPagePeopleDto(Page<People> peoples) {
        return peoples.map(PeopleDto::new);
    }

}
