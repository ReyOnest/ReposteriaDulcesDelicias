package cakeshopapp.services.outputport;

import cakeshopapp.domain.Customer;
import java.util.List;

public interface CustomerPersistencePort {
    void save(Customer customer);
    Customer findById(int id);
    Customer findByEmail(String email);
    void update(Customer customer);
    void deleteById(int id);
    List<Customer> findAll();
}