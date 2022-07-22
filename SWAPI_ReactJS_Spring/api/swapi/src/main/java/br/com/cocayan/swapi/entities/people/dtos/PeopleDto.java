package br.com.cocayan.swapi.entities.people.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cocayan.swapi.entities.people.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDto {

    // @JsonIgnore
    @JsonProperty("people_id")
    private Long peopleId;

    private String name;
    
    private float height;
    
    private float mass;

    @JsonProperty("hair_color")
    private String hairPeople;

    @JsonProperty("skin_color")
    private String skinPeople;

    @JsonProperty("eye_color")
    private String eyePeople;
    
    @JsonProperty("birth_year")
    private String birthYear;
                            
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime created;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime updated;

    public PeopleDto(People people) {
        this.peopleId = people.getPeopleId();
        this.name = people.getName();
        this.height = people.getHeight();
        this.mass = people.getMass();

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

        if (people.getBirthYear() != null) {
            String year = people.getBirthYear().getYear();
            year = year.substring(0, year.indexOf("-"));

            this.birthYear = year + people.getBirthYear().getAge();
        } else {
            this.gender = "unknown";
        }
        
        if (people.getGender() != null) {
            this.gender = people.getGender().getName();
        } else {
            this.gender = "n/a";
        }

        this.created = people.getCreated();
        this.updated = people.getUpdated();
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
