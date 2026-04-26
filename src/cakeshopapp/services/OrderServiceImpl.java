package cakeshopapp.services;

import cakeshopapp.domain.Customer;
import cakeshopapp.domain.Order;
import cakeshopapp.domain.Product;
import cakeshopapp.domain.enums.OrderState;
import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.repository.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public double calculateTotal(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public void processOrder(Customer customer, List<Product> products) {
        System.out.println("--- GENERANDO PEDIDO: DULCES DELICIAS ---");

        for (Product product : products) {
            if (product.getStock() > 0) {
                product.setStock(product.getStock() - 1);

                if (product.getStock() == 0) {
                    product.setState(ProductState.OUT_OF_STOCK);
                }
                System.out.println("Item: " + product.getName() + " | Stock restante: " + product.getStock());
            } else {
                System.out.println("ALERTA: No hay stock suficiente para " + product.getName());
            }
        }

        double finalPrice = calculateTotal(products);

        Order newOrder = new Order(1, customer, products, finalPrice);
        orderRepository.save(newOrder);

        System.out.println("---------------------------------------");
        System.out.println("Cliente: " + customer.getName());
        System.out.println("Total venta: $" + finalPrice);
        System.out.println("Estado del pedido: " + OrderState.PENDING);
        System.out.println("¡Pedido guardado e inventario actualizado!");
    }
}