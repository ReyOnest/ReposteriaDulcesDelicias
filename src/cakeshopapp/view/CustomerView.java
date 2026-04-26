package cakeshopapp.view;

import cakeshopapp.domain.Customer;
import cakeshopapp.repository.CustomerRepository;
import java.util.Scanner;

public class CustomerView {

    private final CustomerRepository customerRepository;
    private final Scanner sc;

    public CustomerView(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.sc = new Scanner(System.in);
    }

    public void createCustomer() {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");

        System.out.print("Ingrese ID (Cédula): ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.print("Apellido: ");
        String lastName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Dirección: ");
        String address = sc.nextLine();

        System.out.print("Ciudad: ");
        String city = sc.nextLine();

        Customer newCustomer = new Customer(id, name, lastName, email, password, true, address, city);

        customerRepository.save(newCustomer);
        System.out.println("¡Cliente registrado exitosamente!");
        System.out.println(newCustomer.toString());
    }

    public void getCustomerById(int id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            System.out.println("\n--- DATOS DEL CLIENTE ---");
            System.out.println(customer);
        } else {
            System.out.println("Error: Cliente con ID " + id + " no encontrado.");
        }
    }

    public void updateCustomer() {
        System.out.print("Ingrese el ID del cliente a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            System.out.print("Nuevo nombre (actual: " + customer.getName() + "): ");
            customer.setName(sc.nextLine());
            System.out.println("¡Datos actualizados!");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void deleteCustomer() {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = sc.nextInt();

        // Aquí llamamos al método delete de tu repositorio
        customerRepository.deleteCustomer(id);
        System.out.println("Proceso de eliminación finalizado.");
    }
}