package cakeshopapp.view;

import cakeshopapp.domain.Admin;
import cakeshopapp.domain.Customer;
import cakeshopapp.services.input.AdminService;
import cakeshopapp.services.input.CustomerService;
import java.util.Scanner;

public class AdminView {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final Scanner sc;

    public AdminView(AdminService adminService, CustomerService customerService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.sc = new Scanner(System.in);
    }

    // Menú modificación de Administradores
    public void createAdmin() {
        System.out.println("\n--- REGISTRO DE ADMINISTRADOR ---");

        System.out.print("Ingrese ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.print("Apellido: ");
        String lastName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Admin newAdmin = new Admin(id, name, lastName, email, password, true, "Admin", "All");

        adminService.registerAdmin(newAdmin);
        System.out.println("\n¡Administrador registrado exitosamente en el sistema!");
        System.out.println(newAdmin.toString());
    }

    public void getAdminById(int id) {
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            System.out.println("\n--- DATOS DEL ADMINISTRADOR ---");
            System.out.println(admin);
        } else {
            System.out.println("Error: Administrador con ID " + id + " no encontrado.");
        }
    }

    public void updateAdmin() {
        System.out.print("Ingrese el ID del administrador a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            System.out.print("Nuevo nombre (actual: " + admin.getName() + "): ");
            admin.setName(sc.nextLine());

            adminService.updateAdminData(admin);
            System.out.println("¡Datos actualizados!");
        } else {
            System.out.println("Administrador no encontrado.");
        }
    }

    public void deleteAdmin() {
        System.out.print("Ingrese el ID del administrador a eliminar: ");
        int id = sc.nextInt();

        adminService.deleteAdminSystem(id);
        System.out.println("Proceso de eliminación ejecutado.");
    }

    // Menú modificación de Clientes
    public void getAllCustomers() {
        System.out.println("\n--- LISTA DE CLIENTES REGISTRADOS EN DULCES DELICIAS ---");
        if (customerService.listAllCustomers().isEmpty()) {
            System.out.println("No hay clientes registrados en la base de datos de MySQL.");
        } else {
            customerService.listAllCustomers().forEach(System.out::println);
        }
    }

    public void createCustomer() {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");

        System.out.print("Ingrese ID (Cédula): ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

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

        customerService.registerCustomer(newCustomer);
        System.out.println("¡Cliente registrado exitosamente!");
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
        sc.nextLine(); // Limpiar buffer

        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            System.out.print("Nuevo nombre (actual: " + customer.getName() + "): ");
            customer.setName(sc.nextLine());

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
}