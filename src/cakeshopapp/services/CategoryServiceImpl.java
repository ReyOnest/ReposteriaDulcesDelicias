package cakeshopapp.services;

import cakeshopapp.domain.Category;
import cakeshopapp.domain.enums.CategoryStateEnum;
import cakeshopapp.services.input.CategoryService; // <-- ¡AGREGA ESTA LÍNEA!
import cakeshopapp.services.outputport.CategoryPersistencePort;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryServiceImpl(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category createCategory(String name, CategoryStateEnum status) {
        Category category = new Category();
        category.setNameCategory(name);
        category.setState(status);

        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryPersistencePort.findCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.findAllCategories();
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryPersistencePort.deleteCategory(id);
    }
}