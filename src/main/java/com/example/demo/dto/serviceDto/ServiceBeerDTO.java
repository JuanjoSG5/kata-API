package com.example.demo.dto.serviceDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ServiceBeerDTO {

    @NotBlank
    private String name;

    @Min(0)
    private Float abv;

    @Min(0)
    private Float ibu;

    @Min(0)
    private Float srm;

    private String descript;
}
