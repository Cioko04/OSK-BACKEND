package com.example.osk.controller;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public void addNewCategory(@Valid @RequestBody Category category){
        categoryService.saveCategory(category);
    }

    @DeleteMapping(path = "{categoryId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Record deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(required = false) CategoryType categoryType,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer time) {
        categoryService.updateCategory(
                categoryId,
                categoryType,
                price,
                time);

        return new ResponseEntity<>("Record updated! ", HttpStatus.OK);
    }
}
