package cakeshopapp.domain;

import java.util.List;

public class Customer extends Person {

    // Atributos propios de Customer
    private String address;
    private String city;

    // Constructores
    public Customer(int id, String name, String lastName, String email, String password, boolean status, String address, String city) {
        super(id, name, lastName, email, password, status);
        this.address = address;
        this.city = city;
    }
    // Constructor vacío
    public Customer(){
        super();
    }

    public Customer(String address, String city){
        this.address = address;
        this.city = city;
    }

    // Implementación heredada de Person
    @Override
    public void showInformation() {
        System.out.println("Cliente: " + getName() + " " + getLastName() + " | Ciudad: " + city);
    }

    // Getters y Setters
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    // Métodos Propios de Customer
    public void getPersonById(int id, Customer customer) {
        // Usamos getId() en lugar de this.id
        if(getId() == id){
            System.out.println("Id: " + customer.getId() + "\n" +
                    "Nombre: " + customer.getName() + "\n");
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "DATOS DEL CLIENTE{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}