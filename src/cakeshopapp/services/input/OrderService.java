package cakeshopapp.services.input;

import cakeshopapp.domain.*;
import cakeshopapp.domain.enums.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    double calculateTotal(List<Product> products);
    void processOrder(Customer customer, List<Product> products);
    void createOrder(Order order);
    Optional<Order> getOrderById(int idOrder); // Cambiado Object por Order
    List<Order> getAllOrders();
    void deleteOrder(int idOrder);
    void updateOrder(Order order); // Asegúrate de tener este

    // Métodos de filtrado como contratos
    List<Order> getOrdersByDate(LocalDate date);
    List<Order> getOrdersByPaidMethod(PaidMethodEnum method);
    List<Order> getOrdersByCustomer(int customerId);
    List<Order> getOrdersByCategory(Class<? extends Product> category);
}