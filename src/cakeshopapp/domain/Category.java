package cakeshopapp.domain;

import cakeshopapp.domain.enums.CategoryStateEnum;

public class Category {

    // Atributos
    private int idCategory;
    private String nameCategory;
    private String description;
    private CategoryStateEnum state;

    // Constructor vacío
    public Category() {
    }

    // Constructor completo
    public Category(int idCategory, String nameCategory, String description, CategoryStateEnum state) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.description = description;
        this.state = state;
    }

    // Getters and Setters
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryStateEnum getState() {
        return state;
    }

    public void setState(CategoryStateEnum state) {
        this.state = state;
    }

    // Método toString
    @Override
    public String toString() {
        return nameCategory;
    }
}