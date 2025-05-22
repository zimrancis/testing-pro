package com.cs.enotes.service.impl;


import com.cs.enotes.entity.Category;
import com.cs.enotes.repository.CategoryRepository;
import com.cs.enotes.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Boolean saveCategory(Category category) {

        category.setIsDeleted(false);

        Category saveCategory = categoryRepo.save(category);

        if (ObjectUtils.isEmpty(saveCategory)) {

            return false;
        }
        return true;
    }

    @Override
    public List<Category> getAllCategory() {

        List<Category> categoryList = categoryRepo.findAll();

        return categoryList;
    }
}
