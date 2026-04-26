package cakeshopapp.domain;

import cakeshopapp.domain.enums.OrderState;

import java.util.List;

public class Order {

    // Atributos de Order
    int orderId;
    Customer customer;
    List<Product> items;
    double totalPrice;
    OrderState status;

    // Constructores
    public Order(int orderId, Customer customer, List<Product> items, double totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;

    }

    // Getter and Setter
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
    }

    public Customer getCustomer() { return this.customer;
    }
}
