package br.com.cocayan.swapi.entities.color.dtos;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cocayan.swapi.entities.color.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDto {
    
    @JsonProperty("color_id")
    private Long colorId;

    private String name;

    public ColorDto(Color color) {
        this.colorId = color.getColorId();
        this.name = color.getName();
    }

    public static Page<ColorDto> pageColorToPageColorDto(Page<Color> colors) {
        return colors.map(ColorDto::new);
    }

}
