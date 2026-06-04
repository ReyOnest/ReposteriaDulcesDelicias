package cakeshopapp.view;

import cakeshopapp.services.CategoryStateSelector;
import cakeshopapp.services.input.CategoryService;
import cakeshopapp.utils.FormValidator;
import cakeshopapp.domain.enums.CategoryStateEnum; // Importante importar el Enum

public class CategoryView {

    private final CategoryService categoryUseCase;

    public CategoryView(CategoryService categoryUseCase){
        this.categoryUseCase = categoryUseCase;
    }

    public void createCategory(){

        String description = FormValidator.validateString("Ingrese la descripción de la categoría: ");

        CategoryStateEnum status = CategoryStateSelector.selectCategoryState();

        categoryUseCase.createCategory(description, status);

        System.out.println("Categoría creada exitosamente.");
    }
}