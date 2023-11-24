package com.example.demo.servicies;


import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import com.example.demo.repos.BeerRepository;
import com.example.demo.servicies.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerService extends BaseService<Beer, Long, BeerRepository> {




    public Beer newBeer(ServiceBeerDTO newBeerParam) {

        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Beer newBeer = Beer.builder()
                .name(newBeerParam.getName())
                .abv(newBeerParam.getAbv())
                .ibu(newBeerParam.getIbu())
                .srm(newBeerParam.getSrm())
                .descript(newBeerParam.getDescript())
                .build();

        return this.save(newBeer);

    }


    public Page<Beer> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findBeerByNameContainsIgnoreCase(txt, pageable);
    }

    public Page<Beer> findCategoryByArgs(final Optional<String> beerName, Pageable pageable) {

        Specification<Beer> specBeerName = (root, query, criteriaBuilder) -> {
            if (beerName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + beerName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };




        return this.repositorio.findAll(specBeerName, pageable);
    }
}
