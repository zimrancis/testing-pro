package com.cs.enotes.controller;

import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;
import com.cs.enotes.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        Boolean saveCategory = categoryService.saveCategory((categoryDTO));

        if (saveCategory) {
            return new ResponseEntity<>("saved success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDTO> allCategories = categoryService.getAllCategory();

        if(CollectionUtils.isEmpty(allCategories)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @GetMapping("/active-category")
    public ResponseEntity<?> getActiveCategories() {
        List<CategoryResponse> allCategories = categoryService.getActiveCategory();

        if(CollectionUtils.isEmpty(allCategories)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }
}

