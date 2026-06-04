package cakeshopapp.domain;

import cakeshopapp.domain.enums.ProductState;

import java.util.List;

// Clase abstracta
public abstract class Product {

    // Atributos de la clase Product
    private int idProduct;
    private String name;
    private String flavor;
    private double price;
    private int stock;
    private ProductState state;
    private Category category;

    //Constructores de la clase Product
    public Product(int idProduct, String name, String flavor, double price, int stock, ProductState state, Category category) {
        this.idProduct = idProduct;
        this.name = name;
        this.flavor = flavor;
        this.price = price;
        this.stock = stock;
        this.state = state;
        this.category = category;
    }
    public Product() {
    }

    public Product(String name){
        this.name = name;
    }

    public Product(boolean status){
        this.state = state;
    }

    //Getters and Setters
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFlavor() { return flavor;
    }
    public void setFlavor(String flavor) { this.flavor = flavor;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public ProductState getState() {
        return state;
    }
    public void setState(ProductState state) {
        this.state = state;
    }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    //Métodos propios de la clase Product

    public <T> void createElement( T product){ }

    public Product createProduct(Product product){
        return product;
    }

    public Product updateProduct(Product product){
        return product;
    }

    public List<Product> getAllProducts(){
        return null;
    }

    public Person getProductById(int idProduct){
        return null;
    }

    public void deleteProduct(int idProduct){ }

    // Método abstracto
    public abstract void showInformation();

    // Método toString
    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", description='" + name + '\'' +
                ", flavor=" + flavor +
                ", price=" + price +
                ", stock=" + stock +
                ", state=" + state +
                '}';
    }

}
