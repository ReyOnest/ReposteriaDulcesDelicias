package cakeshopapp.domain;

import cakeshopapp.domain.enums.ProductState;

public class Cake extends Product {

    // Atributos propios de Cake
    private int slices;

    // Constructor Principal
    public Cake(int idProduct, String name, String flavor, double price, int stock, ProductState state) {
        super(idProduct, name, flavor, price, stock, state);
        this.slices = 0; // Se inicializa en 0 y luego usas el setter
    }

    // Constructor vacío
    public Cake() {
        super();
    }

    // Getters y Setters
    public int getSlices() { return slices; }
    public void setSlices(int slices) { this.slices = slices; }

    // Implementación heredada de Product
    @Override
    public void showInformation() {
        System.out.println("--- DETALLES DE LA TORTA ---");
        System.out.println("Producto: " + getName());
        System.out.println("Sabor: " + getFlavor());
        System.out.println("Porciones: " + slices);
        System.out.println("Precio: $" + getPrice());
        System.out.println("Estado: " + getState());
    }

    // Métodos Propios de Cake
    public void getProductById(int idProduct, Cake cake) {
        if (getIdProduct() == idProduct) {
            System.out.println("Id: " + cake.getIdProduct() + "\n" +
                    "Nombre: " + cake.getName() + "\n" +
                    "Sabor: " + cake.getFlavor());
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Cake{" +
                "id=" + getIdProduct() +
                ", name='" + getName() + '\'' +
                ", flavor='" + getFlavor() + '\'' +
                ", slices=" + slices +
                ", price=" + getPrice() +
                ", state=" + getState() +
                '}';
    }
}