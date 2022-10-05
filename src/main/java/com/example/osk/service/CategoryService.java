package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.model.Student;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category getCategory(Long id);

    Category saveCategory(Category category);

    void deleteCategory(Long id);

    @Transactional
    Category updateCategory(Long id,
                          CategoryType categoryType,
                          Integer price,
                          Integer time);
}
