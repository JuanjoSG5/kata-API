package com.example.demo.dto.converter;

import com.example.demo.dto.StyleDTO;
import com.example.demo.dto.serviceDto.ServiceStyleDTO;
import com.example.demo.entities.Style;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StyleDTOConverter {
    private final ModelMapper modelMapper;

    public ServiceStyleDTO convertToDto(Style style) {
        return modelMapper.map(style, ServiceStyleDTO.class);
    }
}
