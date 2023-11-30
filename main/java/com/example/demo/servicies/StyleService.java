package com.example.demo.servicies;

import com.example.demo.dto.serviceDto.ServiceStyleDTO;
import com.example.demo.entities.Style;
import com.example.demo.repos.StyleRepository;
import com.example.demo.servicies.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StyleService  extends BaseService<Style, Long, StyleRepository> {



    public Style newCategory(ServiceStyleDTO newStyleParam) {
        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Style newStyle = Style.builder()
                .styleName(newStyleParam.getStyleName())
                .build();
        return this.save(newStyle);
    }

    public Page<Style> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findStyleByStyleNameContainingIgnoreCase(txt, pageable);
    }

    public Page<Style> findCategoryByArgs(final Optional<String> categoryName, Pageable pageable) {

        Specification<Style> specBreweryName = (root, query, criteriaBuilder) -> {
            if (categoryName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("style_name")),"%" + categoryName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };
        return this.repositorio.findAll(specBreweryName, pageable);
    }
}


