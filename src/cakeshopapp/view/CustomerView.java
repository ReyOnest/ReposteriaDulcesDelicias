package cakeshopapp.view;

import cakeshopapp.domain.Customer;
import cakeshopapp.services.input.CustomerService;
import java.util.Scanner;

public class CustomerView {

    private final CustomerService customerService;
    private final Scanner sc;

    public CustomerView(CustomerService customerService) {
        this.customerService = customerService;
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

        // Llama al servicio del core en lugar del repositorio en memoria
        customerService.registerCustomer(newCustomer);
        System.out.println("¡Cliente registrado exitonamente!");
        System.out.println(newCustomer.toString());
    }

    public void getCustomerById(int id) {
        Customer customer = customerService.getCustomerById(id);
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

        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            System.out.print("Nuevo nombre (actual: " + customer.getName() + "): ");
            customer.setName(sc.nextLine());

            // Actualiza a través de la capa de servicio mapeada a la BD
            customerService.updateCustomerData(customer);
            System.out.println("¡Datos actualizados!");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void deleteCustomer() {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = sc.nextInt();

        customerService.deleteCustomerSystem(id);
        System.out.println("Proceso de eliminación ejecutado.");
    }

    //Método para que el cliente edite sus datos personales.
    public void updateMyProfile(Customer customer) {
        System.out.println("\n--- EDITAR MI PERFIL ---");
        System.out.println("(Presione Enter si desea mantener el valor actual)");

        // 1. Nombre
        System.out.print("Nombre actual [" + customer.getName() + "]: ");
        String input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setName(input);

        // 2. Apellido
        System.out.print("Apellido actual [" + customer.getLastName() + "]: ");
        input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setLastName(input);

        // 3. Email
        System.out.print("Email actual [" + customer.getEmail() + "]: ");
        input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setEmail(input);

        // 4. Contraseña
        System.out.print("Nueva contraseña (o Enter para mantener la actual): ");
        input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setPassword(input);

        // 5. Dirección
        System.out.print("Dirección actual [" + customer.getAddress() + "]: ");
        input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setAddress(input);

        // 6. Ciudad
        System.out.print("Ciudad actual [" + customer.getCity() + "]: ");
        input = sc.nextLine();
        if (!input.trim().isEmpty()) customer.setCity(input);

        // Persistir cambios
        customerService.updateCustomerData(customer);
        System.out.println("\n¡Perfil actualizado exitosamente!");
    }
}