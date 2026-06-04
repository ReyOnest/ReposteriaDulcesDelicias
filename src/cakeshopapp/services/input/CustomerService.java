package cakeshopapp.services.input;

import cakeshopapp.domain.Customer;
import java.util.List;
import java.util.Optional; // Necesitas importar esto

public interface CustomerService {
    void registerCustomer(Customer customer);

    // Cambiamos el tipo de retorno a Optional<Customer>
    Optional<Customer> findById(int id);

    Customer getCustomerById(int id); // Puedes mantener este si lo usas en otro lado
    Customer getCustomerByEmail(String email);
    void updateCustomerData(Customer customer);
    void deleteCustomerSystem(int id);
    List<Customer> listAllCustomers();
}