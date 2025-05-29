package com.cs.enotes.service;

import com.cs.enotes.entity.Category;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(Category category);
    public List<Category> getAllCategory();

}
