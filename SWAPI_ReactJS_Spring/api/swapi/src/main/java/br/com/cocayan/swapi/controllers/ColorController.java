package br.com.cocayan.swapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cocayan.swapi.entities.color.Color;
import br.com.cocayan.swapi.entities.color.dtos.ColorDto;
import br.com.cocayan.swapi.entities.color.dtos.CreateColorDto;
import br.com.cocayan.swapi.entities.color.dtos.UpdateColorDto;
import br.com.cocayan.swapi.services.ColorService;

@RestController
@RequestMapping("/color")
public class ColorController {
    
    @Autowired
    ColorService colorService;

    @GetMapping
    public Page<ColorDto> getAllColor(
        @PageableDefault(
            sort = "colorId",
            direction = Direction.ASC,
            page = 0,
            size = 10
        ) Pageable pageable
    ) {
        Page<Color> colors = colorService.getAllColors(pageable);
        return ColorDto.pageColorToPageColorDto(colors);
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<ColorDto> getColorById(@PathVariable Long colorId) {
        Optional<Color> optional = colorService.getColorById(colorId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(new ColorDto(optional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ColorDto> createColor(
        @RequestBody CreateColorDto createColorDto,
        UriComponentsBuilder uriBuilder
    ) {
        Color color = createColorDto.createColorDtoToColor(createColorDto);
        colorService.createColor(color);

        URI uri = uriBuilder.path("color/{colorId}").buildAndExpand(color.getColorId()).toUri();
        return ResponseEntity.created(uri).body(new ColorDto(color));
    }

    @PutMapping
    public ResponseEntity<ColorDto> updateColor(@RequestBody UpdateColorDto updateColorDto) {
        Optional<Color> optional = colorService.getColorById(updateColorDto.getColorId());

        if (optional.isPresent()) {
            Color color = updateColorDto.updateColorDtoToColor(updateColorDto);
            return ResponseEntity.ok(new ColorDto(color));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteColor(@RequestBody ColorDto colorDto) {
        Optional<Color> optional = colorService.getColorById(colorDto.getColorId());

        if (optional.isPresent()) {
            if (colorService.deleteColor(colorDto.getColorId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
