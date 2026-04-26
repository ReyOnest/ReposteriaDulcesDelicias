package cakeshopapp.domain;

import cakeshopapp.domain.enums.ProductState;

public class Pastry extends Product {

    // Atributos propios de Pastry
    private String size;

    // Constructor Principal
    public Pastry(int idProduct, String name, String flavor, double price, int stock, ProductState state) {
        super(idProduct, name, flavor, price, stock, state);
        this.size = "None"; // Valor por defecto
    }

    // Constructor vacío
    public Pastry() {
        super();
    }

    // Getters y Setters
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    // Implementación heredada de Product
    @Override
    public void showInformation() {
        System.out.println("--- DETALLES DEL POSTRE/PASTEL ---");
        System.out.println("Producto: " + getName());
        System.out.println("Sabor: " + getFlavor());
        System.out.println("Tamaño: " + size);
        System.out.println("Precio: $" + getPrice());
        System.out.println("Disponibilidad: " + getStock() + " unidades");
    }

    // Métodos Propios de Pastry
    public void getProductById(int idProduct, Pastry pastry) {
        if (getIdProduct() == idProduct) {
            System.out.println("Id: " + pastry.getIdProduct() + "\n" +
                    "Nombre: " + pastry.getName() + "\n" +
                    "Tamaño: " + pastry.getSize());
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Pastry{" +
                "id=" + getIdProduct() +
                ", name='" + getName() + '\'' +
                ", flavor='" + getFlavor() + '\'' +
                ", size='" + size + '\'' +
                ", price=" + getPrice() +
                ", state=" + getState() +
                '}';
    }
}