package com.example.demo.servicies;


import com.example.demo.dto.serviceDto.ServiceBreweryDTO;
import com.example.demo.entities.Breweries;
import com.example.demo.repos.BreweryRepository;
import com.example.demo.servicies.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.util.Optional;
import javax.persistence.criteria.Predicate;




@Service
@RequiredArgsConstructor
public class BreweriesService extends BaseService<Breweries, Long, BreweryRepository> {




    public Breweries newBrewery(ServiceBreweryDTO newBreweryParam) {



        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Breweries newBrewery = Breweries.builder()
                .name(newBreweryParam.getName())
                .address1(newBreweryParam.getAddress1())
                .address2(newBreweryParam.getAddress2())
                .city(newBreweryParam.getCity())
                .state(newBreweryParam.getState())
                .country(newBreweryParam.getCountry())
                .phone(newBreweryParam.getPhone())
                .website(newBreweryParam.getWebsite())
                .descript(newBreweryParam.getDescript())
                .build();

        return this.save(newBrewery);

    }


    public Page<Breweries> findBreweryByName(String txt, Pageable pageable) {
        return this.repositorio.findBreweriesByNameContainsIgnoreCase(txt, pageable);
    }

    public Page<Breweries> findBreweryByArgs(final Optional<String> breweryName, Pageable pageable) {

        Specification<Breweries> specBreweryName = (root, query, criteriaBuilder) -> {
            if (breweryName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + breweryName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };




        return this.repositorio.findAll(specBreweryName, pageable);
    }
}
