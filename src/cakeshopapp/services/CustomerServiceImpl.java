package cakeshopapp.services;

import cakeshopapp.domain.Customer;
import cakeshopapp.services.input.CustomerService;
import cakeshopapp.services.outputport.CustomerPersistencePort;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerPersistencePort customerPersistencePort;

    public CustomerServiceImpl(CustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void registerCustomer(Customer customer) {
        customerPersistencePort.save(customer);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerPersistencePort.findById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerPersistencePort.findByEmail(email);
    }

    @Override
    public void updateCustomerData(Customer customer) {
        customerPersistencePort.update(customer);
    }

    @Override
    public void deleteCustomerSystem(int id) {
        customerPersistencePort.deleteById(id);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerPersistencePort.findAll();
    }
}