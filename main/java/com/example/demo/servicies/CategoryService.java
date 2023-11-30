package com.example.demo.servicies;

import java.util.Optional;

import com.example.demo.dto.serviceDto.ServiceCategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.servicies.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService<Category, Long, CategoryRepository> {



    public Category newCategory(ServiceCategoryDTO newCategoryParam) {



        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Category newCategory = Category.builder()
                .catName(newCategoryParam.getCatName())
                .build();


        return this.save(newCategory);

    }


    public Page<Category> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findCategoryByCatNameContainingIgnoreCase(txt, pageable);
    }

    public Page<Category> findCategoryByArgs(final Optional<String> categoryName, Pageable pageable) {

        Specification<Category> specBreweryName = (root, query, criteriaBuilder) -> {
            if (categoryName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("cat_name")),"%" + categoryName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };




        return this.repositorio.findAll(specBreweryName, pageable);
    }
}


