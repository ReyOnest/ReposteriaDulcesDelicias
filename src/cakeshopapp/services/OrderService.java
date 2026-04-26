package cakeshopapp.services;

import cakeshopapp.domain.Customer;
import cakeshopapp.domain.Product;
import java.util.List;

public interface OrderService {

    double calculateTotal(List<Product> products);
    void processOrder(Customer customer, List<Product> products);
}
