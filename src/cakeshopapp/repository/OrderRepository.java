package cakeshopapp.repository;

import cakeshopapp.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findById(int id) {
        for (Order o : orders) {
            if (o.getOrderId() == id) {
                return o;
            }
        }
        return null;
    }

    public List<Order> findByCustomerId(int customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getId() == customerId) {
                customerOrders.add(o);
            }
        }
        return customerOrders;
    }
}