package com.cs.enotes.service.impl;


import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;
import com.cs.enotes.entity.Category;
import com.cs.enotes.repository.CategoryRepository;
import com.cs.enotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

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
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

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

        List<Category> categories = categoryRepo.findByIsActiveTrue();
        List<CategoryResponse> categoryResponseList = categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryResponse.class))
                .toList();
        return categoryResponseList;
    }
}