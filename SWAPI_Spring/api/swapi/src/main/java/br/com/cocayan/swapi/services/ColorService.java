package br.com.cocayan.swapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cocayan.swapi.entities.color.Color;
import br.com.cocayan.swapi.repositories.ColorRepository;

@Service
public class ColorService {
    
    @Autowired
    ColorRepository colorRepository;

    public Page<Color> getAllColors(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    public Optional<Color> getColorById(Long colorId) {
        return colorRepository.findById(colorId);
    }

    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    public Color updateColor(Color color) {
        Optional<Color> optional = colorRepository.findById(color.getColorId());

        if (optional.isPresent()) {
            Color updateColor = optional.get();
            updateColor.setName(color.getName());

            return colorRepository.save(updateColor);
        } 

        return color;
    }

    public boolean deleteColor(Long colorId) {
        Optional<Color> optional = colorRepository.findById(colorId);

        if (optional.isPresent()) {
            colorRepository.deleteById(colorId);

            return true;
        }

        return false;
    }
    
}
