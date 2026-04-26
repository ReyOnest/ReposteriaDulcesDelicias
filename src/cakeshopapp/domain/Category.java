package cakeshopapp.domain;

public class Category {

    // Atributos propios de Category
    private int idCategory;
    private String nameCategory;
    private String description;

    // Constructor vacío
    public Category() {
    }

    // Constructores
    public Category(int idCategory, String nameCategory, String description) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.description = description;
    }

    // Getters y Setters
    public int getId() {
        return idCategory;
    }

    public void setId(int id) {
        this.idCategory = id;
    }

    public String getName() {
        return nameCategory;
    }

    public void setName(String name) {
        this.nameCategory = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método toString
    @Override
    public String toString() {
        return nameCategory;
    }
}
