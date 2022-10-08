package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.model.User;
import com.example.osk.repository.CategoryRepository;
import com.example.osk.repository.UserRepository;
import com.example.osk.request.CategoryRequest;
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
    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CourseService courseService, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.courseService = courseService;
        this.userRepository = userRepository;
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


    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "category with id " + id + " does not exist"));
    }

    @Override
    public CategoryRequest getCategory(Long id) {
        Category category = getCategoryById(id);
        return new CategoryRequest(
                category.getId(),
                category.getCategoryType(),
                category.getPrice(),
                category.getTime()
        );
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void addCategoryForInstructor(Long inctructorId, Long categoryId) {
        User instructor = userRepository.findById(inctructorId).orElseThrow(() -> new IllegalStateException(
                "User with id " + inctructorId + " does not exist"));
        Category category = getCategoryById(categoryId);
        List<Category> categoryList = instructor.getCategoryList();
        if (!categoryList.contains(category)){
            categoryList.add(category);
            category.getInstructorList().add(instructor);
            categoryRepository.save(category);
            userRepository.save(instructor);
        }else {
            throw new IllegalArgumentException("Instructor already has category with that type!");
        }

    }

    @Override
    public void deleteCategoryFromInstructor(Long inctructorId, Long categoryId){
        User instructor = userRepository.findById(inctructorId).orElseThrow(() -> new IllegalStateException(
                "User with id " + inctructorId + " does not exist"));
        Category category = getCategoryById(categoryId);
        List<Category> categoryList = instructor.getCategoryList();
        if (categoryList.contains(category)){
            categoryList.remove(category);
            category.getInstructorList().remove(instructor);
            categoryRepository.save(category);
            userRepository.save(instructor);
        }else {
            throw new IllegalArgumentException("Instructor hasn't category with that type!");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        courseService.deleteCoursesByCategory(category);
        categoryRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void updateCategory(Long id, CategoryType categoryType, Integer price, Integer time) {
        Category category = getCategoryById(id);
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
