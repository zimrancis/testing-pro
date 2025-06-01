package com.cs.enotes.service;

import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;
import com.cs.enotes.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    static void updateCategory(Integer id, CategoryDTO categoryDTO) {
    }

    public Boolean saveCategory(CategoryDTO categoryDto);

    public CategoryDTO getCategoryById(Integer id);

    public List<CategoryDTO> getAllCategory();

    public List<CategoryResponse> getActiveCategory();

    public CategoryDTO updateCategoryById(Integer id, CategoryDTO updateCategoryDTO) throws Exception;

    public Boolean deleteCategoryById(Integer id);

    Optional<Category> findById(Integer id);

    public void updateTheCategory(Category category);
}
