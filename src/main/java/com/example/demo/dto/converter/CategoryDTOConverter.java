package com.example.demo.dto.converter;

import com.example.demo.dto.BreweryDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.serviceDto.ServiceCategoryDTO;
import com.example.demo.entities.Breweries;
import com.example.demo.entities.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDTOConverter {
    private final ModelMapper modelMapper;

    public ServiceCategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, ServiceCategoryDTO.class);
    }
}
