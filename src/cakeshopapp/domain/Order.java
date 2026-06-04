package cakeshopapp.domain;

import cakeshopapp.domain.enums.OrderState;
import cakeshopapp.domain.enums.PaidMethodEnum;
import java.time.LocalDate; // Asegúrate de importar esto
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<Product> items;
    private double totalPrice;
    private OrderState status;
    private LocalDate orderDate;
    private PaidMethodEnum paidMethod;

    public Order() { // Constructor vacío necesario para los Mappers
    }

    public Order(int orderId, Customer customer, List<Product> items, double totalPrice,
                 OrderState status, LocalDate orderDate, PaidMethodEnum paidMethod) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
        this.paidMethod = paidMethod;
    }

    // Getters and Setters

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Product> getItems() { return items; }
    public void setItems(List<Product> items) { this.items = items; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public OrderState getStatus() { return status; }
    public void setStatus(OrderState status) { this.status = status; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public PaidMethodEnum getPaidMethod() { return paidMethod; }
    public void setPaidMethod(PaidMethodEnum paidMethod) { this.paidMethod = paidMethod; }

    public void validate() {
        if (this.totalPrice <= 0) {
            throw new IllegalArgumentException("Error: El precio total debe ser mayor a cero.");
        }
        if (this.items == null || this.items.isEmpty()) {
            throw new IllegalArgumentException("Error: El pedido debe contener al menos un producto.");
        }
        if (this.customer == null) {
            throw new IllegalArgumentException("Error: El pedido debe estar asociado a un cliente.");
        }
    }
}