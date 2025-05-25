package com.app.service;

import com.app.controller.request.CategoryRequest;
import com.app.dao.CategoryAdapter;
import com.app.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdapter categoryAdapter;

    public List<Category> getCategories() {
        return categoryAdapter.getAll();
    }

    public void addCategory(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getName());
        categoryAdapter.add(category);
    }

}
