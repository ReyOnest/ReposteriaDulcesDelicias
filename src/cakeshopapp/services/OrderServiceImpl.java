package cakeshopapp.services;

import cakeshopapp.domain.*;
import cakeshopapp.domain.enums.*;
import cakeshopapp.services.input.OrderService;
import cakeshopapp.services.outputport.OrderPersistencePort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderPersistencePort orderPersistencePort;

    public OrderServiceImpl(OrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        // Validación del objeto de dominio antes de persistir
        order.validate();
        orderPersistencePort.save(order);
        System.out.println("Pedido procesado y guardado correctamente.");
    }

    @Override
    public void updateOrder(Order order) {
        order.validate();
        orderPersistencePort.update(order);
        System.out.println("Pedido ID " + order.getOrderId() + " actualizado exitosamente.");
    }

    @Override
    public Optional<Order> getOrderById(int idOrder) {
        return Optional.ofNullable(orderPersistencePort.findById(idOrder));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderPersistencePort.findAll();
    }

    @Override
    public void deleteOrder(int idOrder) {
        orderPersistencePort.delete(idOrder);
    }

    // --- MÉTODOS DE FILTRADO (Lógica de Negocio basada en Streams) ---

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return getAllOrders().stream()
                .filter(o -> o.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersByPaidMethod(PaidMethodEnum method) {
        return getAllOrders().stream()
                .filter(o -> o.getPaidMethod().equals(method))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        return getAllOrders().stream()
                .filter(o -> o.getCustomer().getId() == customerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersByCategory(Class<? extends Product> category) {
        return orderPersistencePort.findAll().stream()
                .filter(order -> order.getItems().stream()
                        .anyMatch(product -> category.isInstance(product)))
                .collect(Collectors.toList());
    }

    // --- MÉTODOS DE CÁLCULO Y PROCESAMIENTO ---

    @Override
    public double calculateTotal(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void processOrder(Customer customer, List<Product> products) {
        // Gestión de inventario antes de persistir
        for (Product product : products) {
            if (product.getStock() > 0) {
                product.setStock(product.getStock() - 1);
                if (product.getStock() == 0) {
                    product.setState(ProductState.OUT_OF_STOCK);
                }
            }
        }

        // Creación del objeto de orden (la fecha y el método de pago se definen aquí)
        Order newOrder = new Order(
                0, // ID generado por BD
                customer,
                products,
                calculateTotal(products),
                OrderState.PENDING,
                LocalDate.now(),
                PaidMethodEnum.CASH // Valor por defecto; puede cambiarse en updateOrder
        );

        createOrder(newOrder);
    }
}