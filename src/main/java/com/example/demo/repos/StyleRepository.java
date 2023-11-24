package com.example.demo.repos;


import com.example.demo.entities.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StyleRepository extends JpaRepository<Style,Long>, JpaSpecificationExecutor<Style> {
    @Query("SELECT s FROM Style s WHERE LOWER(s.style_name) LIKE LOWER(CONCAT('%', :styleName, '%'))")
    Page<Style> findStyleByStyleNameContainingIgnoreCase(@Param("styleName") String styleName, Pageable pageable);
}