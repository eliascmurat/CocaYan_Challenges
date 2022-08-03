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
    
    @NotNull(message = "ID não pode ser nulo!")
    private Long colorId;
    
    @NotNull(message = "Nome da cor não pode ser nulo!")
    @Size(min = 3, max = 100, message = "Nome da cor deve ter entre 3 a 50 caracteres")
    private String name;

    public Color updateColorDtoToColor(UpdateColorDto updateColorDto) {
        Color color = new Color();
        color.setColorId(updateColorDto.getColorId());
        color.setName(updateColorDto.getName());

        return color;
    }

}
