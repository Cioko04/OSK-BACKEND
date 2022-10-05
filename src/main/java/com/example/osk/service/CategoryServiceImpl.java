package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.model.Student;
import com.example.osk.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "category with id " + id + " does not exist"));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
//        Category category = getCategory(id);
        categoryRepository.deleteById(id);

    }

    @Override
    public Category updateCategory(Long id, CategoryType categoryType, Integer price, Integer time) {
        Category category = getCategory(id);
        if (categoryType != null &&
                !Objects.equals(category.getCategoryType(), categoryType)) {
            category.setCategoryType(categoryType);
        }
        if (price != null &&
                !Objects.equals(category.getPrice(), price)) {
            category.setPrice(price);
        }
        if (time != null &&
                !Objects.equals(category.getTime(), time)) {
            category.setTime(time);
        }
        return null;
    }
}
