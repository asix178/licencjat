package com.app.dao;

import com.app.dbmodel.CategoryEntity;
import com.app.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAdapter {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities.stream().map(CategoryEntity::toDomain).toList();
    }

    public void add(Category category) {
        categoryRepository.save(CategoryEntity.fromDomain(category));
    }
}
