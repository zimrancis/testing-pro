package com.cs.enotes.service.impl;


import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;
import com.cs.enotes.entity.Category;
import com.cs.enotes.repository.CategoryRepository;
import com.cs.enotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ModelMapper modelMapper;


    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Boolean saveCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        //Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setIsActive(categoryDTO.getIsActive());
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedOn(new Date());
        Category saveCategory = categoryRepo.save(category);

        if (ObjectUtils.isEmpty(saveCategory)) {

            return false;
        }
        return true;
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {

        Optional<Category> findByCategory = categoryRepo.findByIdAndIsDeletedFalse(id);

        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            return modelMapper.map(category, CategoryDTO.class);
        }
        return null;
    }


    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepo.findByAndIsDeletedFalse();

        /*List<CategoryDTO> categoryDtoList = categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                .toList();

        return categoryDtoList;*/
        return categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                .toList();

    }

    @Override
    public List<CategoryResponse> getActiveCategory() {

        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryResponseList = categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryResponse.class))
                .toList();
        return categoryResponseList;
    }

    @Override
    public CategoryDTO updateCategoryById(Integer id, CategoryDTO categoryDTO) {
            Optional<Category> optionalCategory = categoryRepo.findById(id);
            if (optionalCategory.isEmpty()) {
                return null;
            }
            Category category = optionalCategory.get();
            // Map the new values from DTO to the existing entity
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setUpdatedOn(new Date());
            // Add more fields if needed
            Category updatedCategory = categoryRepo.save(category);
            return modelMapper.map(updatedCategory, CategoryDTO.class);
        }


    @Override
    public void updateTheCategory(Category category)   {

        Optional<Category> findById = categoryRepo.findById(category.getId());

        if (findById.isPresent()) {
            Category existCategory = findById.get();
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedOn(category.getCreatedOn());
        }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Sorry! The particular ID you provided does not exist!");
        }
        return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);



    /*@Override
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category with ID " + id + " not found.");
        }
        categoryRepository.deleteById(id);
    }*/


   /* @Override
    public Boolean deleteCategoryById(Integer id) {

        if (!categoryRepo.existsById(id)) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }*/

    @Override
    public Boolean deleteCategoryById(Integer id) {

        Optional<Category> findCategoryById = categoryRepo.findById(id);
        if (findCategoryById.isPresent()) {
            Category category = findCategoryById.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        } else
            return false;

    }


}