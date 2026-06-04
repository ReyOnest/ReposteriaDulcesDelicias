package cakeshopapp.services.input;

import cakeshopapp.domain.Category;
import cakeshopapp.domain.enums.CategoryStateEnum;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(String name , CategoryStateEnum status);
    Optional<Category> getCategoryById(int id);
    List<Category> getAllCategories();
    Category updateCategory(Category category);
    void deleteCategory(int id);
}
