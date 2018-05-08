package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Category;
import com.ucbcba.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    @Qualifier(value = "categoryRepository")
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> listAllCategories() {
        return this.categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public Category getCategory(Integer id) {
        return this.categoryRepository.findOne(id);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.delete(id);
    }
}
