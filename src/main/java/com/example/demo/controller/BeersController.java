package com.example.demo.controller;

import com.example.demo.dto.converter.BeerDTOConverter;
import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import com.example.demo.servicies.BeerService;
import error.BeerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BeersController {

    private final BeerService serviceBeer;
    private final BeerDTOConverter dtoConverter;
    @GetMapping("/beers")
    public Page<ServiceBeerDTO> getBeers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Beer> beerPage = serviceBeer.findAll(pageRequest);

        // Convert the entities to DTOs using your dtoConverter
        List<ServiceBeerDTO> beerDTOList = beerPage.getContent()
                .stream()
                .map(dtoConverter::convertToDto) // Assuming convertToDto method exists in your dtoConverter
                .collect(Collectors.toList());
        return new PageImpl<>(beerDTOList, pageRequest, beerPage.getTotalElements());
    }

    @PostMapping("/beer")
    public ResponseEntity<Beer> createBeer(@RequestBody ServiceBeerDTO beer){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceBeer.newBeer(beer));
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable Long id){
        Optional<Beer> beer = serviceBeer.findById(id);
        return beer.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Beer> deleteBeer(@PathVariable Long id){
        Beer beer = serviceBeer.findById(id).orElseThrow(() -> new BeerNotFound(id));
        serviceBeer.delete(beer);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/beer/{id}")
    public Beer updateBeer(@PathVariable Long id, @RequestBody ServiceBeerDTO updatedBeer) {

        return serviceBeer.findById(id).map(beerToUpdate ->{
            beerToUpdate.setName(updatedBeer.getName());
            beerToUpdate.setAbv(updatedBeer.getAbv());
            beerToUpdate.setIbu(updatedBeer.getIbu());
            beerToUpdate.setSrm(updatedBeer.getSrm());
            beerToUpdate.setDescript(updatedBeer.getDescript());
            return serviceBeer.save(beerToUpdate);
        }).orElseThrow(() -> new BeerNotFound(id));
    }
}
