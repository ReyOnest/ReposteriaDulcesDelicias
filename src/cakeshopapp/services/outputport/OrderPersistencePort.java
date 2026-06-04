package cakeshopapp.services.outputport;

import cakeshopapp.domain.Order;
import java.util.List;

public interface OrderPersistencePort {
    void save(Order order);
    List<Order> findAll();
    Order findById(int id);
    void update(Order order);
    void delete(int id);
}