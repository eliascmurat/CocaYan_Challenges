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

    private String skinPeople;

    private String eyePeople;

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
            String year = people.getBirthYear().getYear();
            year = year.substring(0, year.indexOf("-"));

            this.birthYear = year + people.getBirthYear().getAge();
        } else {
            this.gender = "unknown";
        }

        if (people.getHairPeople().size() > 0) {            
            StringBuffer stringBuffer = new StringBuffer();
            int arraySize = people.getHairPeople().size();

            for (int i = 0; i < arraySize; i++) {
                String color = people.getHairPeople().get(i).getColor().getName();
                stringBuffer.append(fomatedText(color, i, arraySize));
            }

            this.hairPeople = stringBuffer.toString();
        } else {
            this.hairPeople = "unknown";
        }

        if (people.getSkinPeople().size() > 0) {            
            StringBuffer stringBuffer = new StringBuffer();
            int arraySize = people.getSkinPeople().size();

            for (int i = 0; i < arraySize; i++) {
                String color = people.getSkinPeople().get(i).getColor().getName();
                stringBuffer.append(fomatedText(color, i, arraySize));
            }

            this.skinPeople = stringBuffer.toString();
        } else {
            this.skinPeople = "unknown";
        }

        if (people.getEyePeople().size() > 0) {            
            StringBuffer stringBuffer = new StringBuffer();
            int arraySize = people.getEyePeople().size();

            for (int i = 0; i < arraySize; i++) {
                String color = people.getEyePeople().get(i).getColor().getName();
                stringBuffer.append(fomatedText(color, i, arraySize));
            }

            this.eyePeople = stringBuffer.toString();
        } else {
            this.eyePeople = "unknown";
        }
    }

    public String fomatedText(String text, int index, int arraySize) {
        if (index != (arraySize - 1)) {
            text += ", ";
        }

        return text;
    }

    public static Page<PeopleDto> pagePeopleToPagePeopleDto(Page<People> peoples) {
        return peoples.map(PeopleDto::new);
    }

}
