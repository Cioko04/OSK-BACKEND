package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.request.CategoryRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryService {
    List<CategoryRequest> getCategories();

    CategoryRequest getCategory(Long id);

    void saveCategory(Category category);

    void addCategoryForInstructor(Long inctructorId, Long categoryId);

    void deleteCategoryFromInstructor(Long inctructorId, Long categoryId);

    void deleteCategory(Long id);

    @Transactional
    void updateCategory(Long id,
                            CategoryType categoryType,
                            Integer price,
                            Integer time);
}
