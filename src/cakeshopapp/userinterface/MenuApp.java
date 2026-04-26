package cakeshopapp.userinterface;

import cakeshopapp.services.ProductService;
import cakeshopapp.view.AdminView;
import cakeshopapp.view.CustomerView;
import cakeshopapp.view.ProductView;

import java.util.Scanner;

public class MenuApp {

    Scanner sc = new Scanner(System.in);
    private final CustomerView customerView;
    private final AdminView adminView;
    private final ProductView productView;
    private final ProductService productService;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView,  ProductService productService) {
        this.customerView = customerView;
        this.adminView = adminView;
        this.productView = productView;
        this.productService = productService;
    }

    public void showMainMenu() {
        System.out.println("--- BIENVENIDO A REPOSTERÍA DULCES DELICIAS ---");
        System.out.println("Presione 1 para encender el sistema (0 para salir)");

        int init = sc.nextInt();
        sc.nextLine();

        while (init != 0) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión (Acceso a Menús)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\n¿Qué tipo de usuario desea registrar?");
                    System.out.println("1. Cliente");
                    System.out.println("2. Administrador");
                    int userType = sc.nextInt();
                    sc.nextLine();

                    if (userType == 1) {
                        customerView.createCustomer();
                    } else if (userType == 2) {
                        adminView.createAdmin();
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- ACCESO AL SISTEMA ---");
                    System.out.println("¿Cómo desea ingresar?");
                    System.out.println("1. Como Administrador");
                    System.out.println("2. Como Cliente");
                    System.out.print("Seleccione: ");
                    int loginType = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    if (loginType == 1) {
                        System.out.println("\n--- LOGIN ADMINISTRATIVO ---");
                        System.out.print("Email Admin: ");
                        String adminEmail = sc.nextLine();
                        System.out.print("Password Admin: ");
                        String adminPass = sc.nextLine();

                        if (!adminEmail.isEmpty() && !adminPass.isEmpty()) {
                            System.out.println("Acceso concedido al Panel de Control.");
                            showMenuAdmin();
                        } else {
                            System.out.println("Credenciales incorrectas.");
                        }

                    } else if (loginType == 2) {
                        System.out.println("\n--- LOGIN CLIENTE ---");
                        System.out.print("Email: ");
                        String userEmail = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String userPass = sc.nextLine();

                        if (!userEmail.isEmpty() && !userPass.isEmpty()) {
                            System.out.println("Bienvenido de nuevo a Dulces Delicias.");
                            showMenuCustomer();
                        } else {
                            System.out.println("Credenciales incorrectas.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 3:
                    System.out.println("Apagando sistema de Dulces Delicias...");
                    init = 0;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void showMenuAdmin() {
        while (true) {
            System.out.println("\n--- PANEL DE CONTROL: ADMINISTRADOR ---");
            System.out.println("1. Gestionar Inventario (Productos)");
            System.out.println("2. Ver Historial de Ventas");
            System.out.println("3. Gestión de Clientes");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Entrando a gestión de repostería...");
                    productView.createProductMenu(productService);
                    break;
                case 2:
                    System.out.println("Mostrando historial de pedidos...");
                    break;
                case 3:
                    customerMenuAdmin();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Seleccione una opción correcta.");
            }
        }
    }

    public void showMenuCustomer() {
        while (true) {
            System.out.println("\n--- MENÚ DEL CLIENTE ---");
            System.out.println("1. Ver Catálogo de Dulces");
            System.out.println("2. Realizar un Pedido");
            System.out.println("3. Ver mi Perfil");
            System.out.println("4. Volver");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Mostrando catálogo de tortas y postres...");
                    break;
                case 2:
                    System.out.println("Procesando nueva orden...");
                    break;
                case 3:
                    System.out.print("Ingrese su ID para ver datos: ");
                    int id = sc.nextInt();
                    customerView.getCustomerById(id);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void customerMenuAdmin() {
        while (true) {
            System.out.println("\n--- GESTIÓN DE CLIENTES (ADMIN) ---");
            System.out.println("1. Registrar Nuevo Cliente");
            System.out.println("2. Buscar Cliente por ID");
            System.out.println("3. Modificar Datos de Cliente");
            System.out.println("4. Listar Todos los Clientes");
            System.out.println("5. Eliminar Cliente del Sistema");
            System.out.println("6. Volver");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1: customerView.createCustomer(); break;
                case 2:
                    System.out.print("ID a buscar: ");
                    customerView.getCustomerById(sc.nextInt());
                    break;
                case 3: customerView.updateCustomer(); break;
                case 4: adminView.getAllCustomers(); break;
                case 5: customerView.deleteCustomer(); break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    public void start() {
        showMainMenu();
    }
}