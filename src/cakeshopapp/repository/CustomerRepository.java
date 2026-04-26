package cakeshopapp.repository;

import cakeshopapp.domain.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private List<Customer> customers;

    public CustomerRepository() {
        this.customers = new ArrayList<>();
    }

    public void save(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Customer findByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Customer updateCustomer(int id) {
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                return customer;
            }
        }
        return null;
    }

    public void deleteCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }
}