package com.cs.enotes.controller;

import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;
import com.cs.enotes.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        Boolean saveCategory = categoryService.saveCategory((categoryDTO));

        if (saveCategory) {
            return new ResponseEntity<>("saved success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        if (ObjectUtils.isEmpty(categoryDTO)) {
            return new ResponseEntity<>("Category not found with Id =" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDTO> allCategories = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(allCategories)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategories() {
        List<CategoryResponse> allCategories = categoryService.getActiveCategory();

        if (CollectionUtils.isEmpty(allCategories)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        boolean isDeleted = categoryService.deleteCategoryById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Id " + id + " is successfully deleted.");
        } else {
            return /*ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Sorry! The particular id you provided does not exist!");*/
            new ResponseEntity<>("Category is not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

