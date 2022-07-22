package br.com.cocayan.swapi.entities.color.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cocayan.swapi.entities.color.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorDto {
    
    @NotNull
    private Long colorId;
    
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    public Color updateColorDtoToColor(UpdateColorDto updateColorDto) {
        Color color = new Color();
        color.setColorId(updateColorDto.getColorId());
        color.setName(updateColorDto.getName());

        return color;
    }

}
