package cakeshopapp.services.outputport;

import cakeshopapp.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {

    Category saveCategory(Category category);
    Optional<Category> findCategoryById(int id);
    List<Category> findAllCategories();
    Category updateCategory(Category category);
    void deleteCategory(int id);

}
