package com.example.osk.category.service;

import com.example.osk.category.Category;
import com.example.osk.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Category with id " + id + " does not exist"));
    }
}
