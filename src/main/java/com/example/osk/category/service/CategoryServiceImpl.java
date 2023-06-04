package com.example.osk.category.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import com.example.osk.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategory(CategoryType categoryType) {
        return categoryRepository.findByCategoryType(categoryType).orElseThrow(() -> new IllegalStateException(
                "Category with type " + categoryType + " does not exist"));
    }
}
