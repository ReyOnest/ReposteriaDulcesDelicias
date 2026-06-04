package cakeshopapp.domain;

import cakeshopapp.domain.enums.ProductState;

public class Cookie extends Product {

    // Atributos propios de Cookie
    private boolean isCrunchy; // Atributo propio: ¿Es crujiente?

    // Constructor Principal
    public Cookie(int id, String name, String flavor, double price, int stock, ProductState state, boolean isCrunchy, Category category) {
        super(id, name, flavor, price, stock, state, category);
        this.isCrunchy = isCrunchy;
    }

    // Constructor vacío
    public Cookie() {
        super();
    }

    // Getters and Setters
    public boolean isCrunchy() {
        return isCrunchy;
    }
    public void setIsCrunchy(boolean isCrunchy) {
        this.isCrunchy = isCrunchy;
    }

    // Implementación heredada de Product
    @Override
    public void showInformation() {
        System.out.println("--- DETALLES DE LA GALLETA ---");
        System.out.println("Producto: " + getName());
        System.out.println("Sabor: " + getFlavor());
        System.out.println("Tamaño: " + isCrunchy);
        System.out.println("Precio: $" + getPrice());
        System.out.println("Disponibilidad: " + getStock() + " unidades");
    }

    // Métodos Propios de Cookie
    public void getProductById(int idProduct, Cookie cookie) {
        if (getIdProduct() == idProduct) {
            System.out.println("Id: " + cookie.getIdProduct() + "\n" +
                    "Nombre: " + cookie.getName() + "\n" +
                    "Es Crujiente: " + cookie.isCrunchy());
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Cookie{" +
                "id=" + getIdProduct() +
                ", name='" + getName() + '\'' +
                ", flavor='" + getFlavor() + '\'' +
                ", size='" + isCrunchy + '\'' +
                ", price=" + getPrice() +
                ", state=" + getState() +
                '}';
    }
}