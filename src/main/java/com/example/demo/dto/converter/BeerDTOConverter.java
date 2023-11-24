package com.example.demo.dto.converter;


import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeerDTOConverter {

    private final ModelMapper modelMapper;

    public ServiceBeerDTO convertToDto(Beer beer) {
        return modelMapper.map(beer, ServiceBeerDTO.class);
    }
}
