package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.repository.CategoryRepository;
import com.example.osk.request.CategoryRequest;
import com.example.osk.request.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CourseService courseService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CourseService courseService) {
        this.categoryRepository = categoryRepository;
        this.courseService = courseService;
    }

    @Override
    public List<CategoryRequest> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryRequest> categoryRequestList = new ArrayList<>();
        categories.forEach(category -> {
            CategoryRequest categoryRequest = new CategoryRequest(
                    category.getId(),
                    category.getCategoryType(),
                    category.getPrice(),
                    category.getTime()
            );
            categoryRequestList.add(categoryRequest);
        });
        return categoryRequestList;
    }

    @Override
    public List<CategoryRequest> getCategoriesWithCourses() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryRequest> categoryRequestList = new ArrayList<>();
        categories.forEach(category -> {
            CategoryRequest categoryRequest = new CategoryRequest(
                    category.getId(),
                    category.getCategoryType(),
                    category.getPrice(),
                    category.getTime()
            );
            List<CourseRequest> courses = courseService.getCoursesByCategory(category);
            categoryRequest.setCourses(courses);
            categoryRequestList.add(categoryRequest);
        });
        return categoryRequestList;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "category with id " + id + " does not exist"));
    }

    @Override
    public CategoryRequest getCategoryWithCourses(Long id) {
        Category category = getCategory(id);
        CategoryRequest categoryRequest = new CategoryRequest(
                category.getId(),
                category.getCategoryType(),
                category.getPrice(),
                category.getTime()
        );
        List<CourseRequest> courses = courseService.getCoursesByCategory(category);
        categoryRequest.setCourses(courses);
        return categoryRequest;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategory(id);
        courseService.deleteCoursesByCategory(category);
        categoryRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void updateCategory(Long id, CategoryType categoryType, Integer price, Integer time) {
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
        categoryRepository.save(category);
    }
}
