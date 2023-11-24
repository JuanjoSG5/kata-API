package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeerDTO {

    private Long id;
    private String name;
    private Float abv;
    private Float ibu;
    private Float srm;
    private String filepath;
    private String descript;


}
