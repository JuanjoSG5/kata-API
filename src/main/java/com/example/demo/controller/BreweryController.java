package com.example.demo.controller;

import com.example.demo.entities.Breweries;
import com.example.demo.error.BreweryNotFound;
import com.example.demo.servicies.BreweriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repos.BreweryRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class BreweryController {

    private final BreweriesService breweriesService;

    @GetMapping("/breweries")
    public Page<Breweries> getBreweryList(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        return breweriesService.findAll(pageRequest);
    }

    @GetMapping("/brewerie/{id}")
    public ResponseEntity<Breweries> getBrewery(@PathVariable Long id){
        Optional<Breweries> brewery = breweriesService.findById(id);
        return brewery.map(ResponseEntity::ok)
                .orElseThrow(() -> new BreweryNotFound(id));
    }
}
