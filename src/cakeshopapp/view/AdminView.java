package cakeshopapp.view;

import cakeshopapp.domain.Admin;
import cakeshopapp.repository.CustomerRepository; // Usamos el repositorio directamente
import java.util.Scanner;

public class AdminView {

    private final CustomerRepository customerRepository;
    private final Scanner sc;

    public AdminView(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.sc = new Scanner(System.in);
    }

    public void createAdmin() {
        System.out.println("\n--- REGISTRO DE ADMINISTRADOR ---");

        System.out.print("Ingrese ID: ");
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

        Admin newAdmin = new Admin(id, name, lastName, email, password, true, "Admin", "All");

        System.out.println("\n¡Administrador registrado exitosamente en el sistema!");
        System.out.println(newAdmin.toString());
    }

    public void getAllCustomers() {
        System.out.println("\n--- LISTA DE CLIENTES REGISTRADOS EN DULCES DELICIAS ---");
        if (customerRepository.findAll().isEmpty()) {
            System.out.println("No hay clientes registrados aún.");
        } else {
            customerRepository.findAll().forEach(System.out::println);
        }
    }

}