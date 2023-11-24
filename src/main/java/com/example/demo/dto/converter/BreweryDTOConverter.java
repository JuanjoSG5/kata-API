package com.example.demo.dto.converter;

import com.example.demo.dto.BreweryDTO;
import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.dto.serviceDto.ServiceBreweryDTO;
import com.example.demo.entities.Breweries;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BreweryDTOConverter {
    private final ModelMapper modelMapper;

    public ServiceBreweryDTO convertToDto(Breweries brewery) {
        return modelMapper.map(brewery, ServiceBreweryDTO.class);
    }
}
