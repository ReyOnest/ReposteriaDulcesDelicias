package cakeshopapp.domain;

import cakeshopapp.domain.enums.ProductState;

public class Cupcake extends Product {

    // Atributos propios de Cupcake
    private String frosting; // Atributo propio: Tipo de cobertura

    // Constructor Principal
    public Cupcake(int id, String name, String flavor, double price, int stock, ProductState state, String frosting, Category category) {
        super(id, name, flavor, price, stock, state, category);
        this.frosting = frosting; // Se inicializa en 0 y luego usamos el setter
    }

    // Constructor vacío
    public Cupcake() {
        super();
    }

    // Getters y Setters
    public String getFrosting() {
        return frosting;
    }
    public void setFrosting(String frosting) {
        this.frosting = frosting;
    }

    // Implementación heredada de Product
    @Override
    public void showInformation() {
        System.out.println("--- DETALLES DEL CUPCAKE ---");
        System.out.println("Producto: " + getName());
        System.out.println("Sabor: " + getFlavor());
        System.out.println("Tipo de cobertura: " + frosting);
        System.out.println("Precio: $" + getPrice());
        System.out.println("Estado: " + getState());
    }

    // Métodos Propios de Cupcake
    public void getProductById(int idProduct, Cupcake cupcake) {
        if (getIdProduct() == idProduct) {
            System.out.println("Id: " + cupcake.getIdProduct() + "\n" +
                    "Nombre: " + cupcake.getName() + "\n" +
                    "Sabor: " + cupcake.getFlavor());
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Cupcake{" +
                "id=" + getIdProduct() +
                ", name='" + getName() + '\'' +
                ", flavor='" + getFlavor() + '\'' +
                ", slices=" + frosting +
                ", price=" + getPrice() +
                ", state=" + getState() +
                '}';
    }
}