package com.example.demo.repos;

import com.example.demo.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    @Query("SELECT c FROM Category c WHERE LOWER(c.cat_name) LIKE LOWER(CONCAT('%', :catName, '%'))")
    Page<Category> findCategoryByCatNameContainingIgnoreCase(@Param("catName") String catName, Pageable pageable);
}