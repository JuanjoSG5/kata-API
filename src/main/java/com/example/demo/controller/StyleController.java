package com.example.demo.controller;


import com.example.demo.entities.Style;
import com.example.demo.error.StyleNotFound;
import com.example.demo.servicies.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StyleController {


    private final StyleService styleService;

    @GetMapping("/styles")
    public Page<Style> getStyles(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return styleService.findAll(pageRequest);
    }

    @GetMapping("/style/{id}")
    public ResponseEntity<Style> getStyle(@PathVariable Long id) {
        Optional<Style> style = styleService.findById(id);
        return style.map(ResponseEntity::ok)
                .orElseThrow(() -> new StyleNotFound(id));
    }

}
