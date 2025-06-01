package com.cs.enotes.service;

import com.cs.enotes.dto.CategoryDTO;
import com.cs.enotes.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(CategoryDTO categoryDto);

    public CategoryDTO getCategoryById(Integer id);

    public List<CategoryDTO> getAllCategory();

    public List<CategoryResponse> getActiveCategory();

    /*public CategoryDTO updateCategoryById(Integer id) throws Exception;*/

    public Boolean deleteCategoryById(Integer id);
}
