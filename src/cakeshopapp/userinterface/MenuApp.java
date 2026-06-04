package cakeshopapp.userinterface;

import cakeshopapp.domain.*;
import cakeshopapp.domain.enums.PaidMethodEnum;
import cakeshopapp.services.OrderPaidMethodSelector;
import cakeshopapp.services.input.CategoryService;
import cakeshopapp.services.input.CustomerService;
import cakeshopapp.services.input.OrderService;
import cakeshopapp.services.input.ProductService;
import cakeshopapp.utils.FormValidator;
import cakeshopapp.utils.date.DateValidator;
import cakeshopapp.view.AdminView;
import cakeshopapp.view.CustomerView;
import cakeshopapp.view.OrderView;
import cakeshopapp.view.ProductView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuApp {

    private final Scanner sc = new Scanner(System.in);
    private final CustomerView customerView;
    private final AdminView adminView;
    private final ProductView productView;
    private final OrderView orderView;
    private final ProductService productService;
    private final OrderService orderService;
    private final CategoryService categoryService;
    private final CustomerService customerService;

    private Customer currentCustomer;

    public MenuApp(CustomerView customerView, AdminView adminView, ProductView productView, OrderView orderView, ProductService productService, OrderService orderService, CategoryService categoryService, CustomerService customerService) {
        this.customerView = customerView;
        this.adminView = adminView;
        this.productView = productView;
        this.orderView = orderView;
        this.productService = productService;
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.customerService = customerService;
    }

    // Método para asignar el cliente cuando alguien inicia sesión
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    // Getter para usarlo en los menús
    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    // Iniciar / Apagar Sistema
    public void showMainMenu() {
        System.out.println("--- BIENVENIDO A REPOSTERÍA DULCES DELICIAS ---");
        System.out.println("Presione 1 para encender el sistema (0 para salir)");

        int init = sc.nextInt();
        sc.nextLine();
        // Menú Principal
        while (init != 0) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión (Acceso a Menús)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine();
            // Menú Registro
            switch (option) {
                case 1:
                    System.out.println("\n¿Qué tipo de usuario desea registrar?");
                    System.out.println("1. Cliente");
                    System.out.println("2. Administrador");
                    System.out.println("3. Volver al Menú Principal");
                    int userType = sc.nextInt();
                    sc.nextLine();

                    if (userType == 1) {
                        customerView.createCustomer();
                    } else if (userType == 2) {
                        adminView.createAdmin();
                    } else if (userType == 3) {
                        System.out.println("Volviendo al menú principal...");
                        return;
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                // Menú Login
                case 2:
                    System.out.println("\n--- ACCESO AL SISTEMA ---");
                    System.out.println("¿Cómo desea ingresar?");
                    System.out.println("1. Como Administrador");
                    System.out.println("2. Como Cliente");
                    System.out.println("3. Volver al Menú Principal");
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
                            // 1. Buscamos al cliente en la base de datos usando el servicio
                            Customer clienteLogueado = customerService.getCustomerByEmail(userEmail);

                            // 2. Validamos que el cliente exista
                            if (clienteLogueado != null) {
                                System.out.println("Bienvenido de nuevo a Dulces Delicias.");
                                // 3. Pasamos el objeto recuperado al método
                                showMenuCustomer(clienteLogueado);
                            } else {
                                System.out.println("Error: Email no registrado o credenciales incorrectas.");
                            }
                        } else {
                            System.out.println("Por favor, complete todos los campos.");
                        }
                    } else if (loginType == 3) {
                        System.out.println("Volviendo al menú principal...");
                        return;
                    }else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                // Salir del Sistema
                case 3:
                    System.out.println("Apagando Sistema de la Repostería Dulces Delicias...");
                    init = 0;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    // Menú Admin
    public void showMenuAdmin() {
        while (true) {
            System.out.println("\n--- PANEL DE CONTROL: ADMINISTRADOR ---");
            System.out.println("1. Gestión de Inventario (Productos)");
            System.out.println("2. Gestión de Pedidos");
            System.out.println("3. Ver Historial de Ventas");
            System.out.println("4. Gestión de Clientes");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Entrando a Gestión de Inventario...");
                    productMenuAdmin();
                    break;
                case 2:
                    System.out.println("Mostrando Historial de Pedidos ...");
                    orderMenuAdmin();
                case 3:
                    System.out.println("Mostrando Historial de Ventas...");
                    salesMenuAdmin();
                    break;
                case 4:
                    System.out.println("Mostrando Gestión de Clientes...");
                    customerMenuAdmin();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    return;
                default:
                    System.out.println("Seleccione una opción correcta.");
            }
        }
    }
    // Menú Clientes
    public void showMenuCustomer(Customer currentCustomer) {
        // Validación de seguridad
        if (currentCustomer == null) {
            System.out.println("Error: No se ha podido identificar al cliente. Iniciando sesión de nuevo...");
            return;
        }

        while (true) {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1. Ver Catálogo de Productos");
            System.out.println("2. Realizar un Pedido");
            System.out.println("3. Editar/Eliminar un Pedido en Curso");
            System.out.println("4. Ver Mi Historial de Pedidos");
            System.out.println("5. Ver/Editar Mi Perfil");
            System.out.println("6. Salir al Menú Principal");
            System.out.print("Seleccione una opción: ");

            // Usamos nextLine y parseo para evitar problemas de buffer con nextInt
            String input = sc.nextLine();
            int option = 0;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("Mostrando Catálogo de Productos ...");
                    productView.listAllProducts(productService);
                    break;
                case 2:
                    System.out.println("Procesando Nueva Orden...");
                    // Aquí ya tenemos la certeza de que currentCustomer no es nulo
                    orderView.createOrderForCustomer(currentCustomer);
                    break;
                case 3:
                    System.out.println("Editar/Eliminar una Orden Pendiente...");
                    // Pedimos el ID de la orden para editarla
                    int idToEdit = FormValidator.validateInt("Ingrese el ID de su pedido a gestionar: ");
                    orderView.updateOrder(idToEdit);
                    break;
                case 4:
                    System.out.println("Mostrando su historial de Pedidos...");
                    orderView.showOrdersByCustomer(currentCustomer.getId());
                    break;
                case 5:
                    System.out.print("Ver/Editar sus Datos: ");
                    System.out.println("\n1. Ver mis datos");
                    System.out.println("2. Editar mis datos");
                    System.out.println("3. Volver al Menú Principal");
                    System.out.print("Seleccione: ");

                    int choice;
                    try {
                        choice = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida.");
                        break;
                    }

                    switch (choice) {
                        case 1:
                            customerView.getCustomerById(currentCustomer.getId());
                            break;
                        case 2:
                            customerView.updateMyProfile(currentCustomer);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    // Menú Admin 1. Gestión de Productos
    public void productMenuAdmin() {
        while (true) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS (ADMIN) ---");
            System.out.println("1. Crear Producto");
            System.out.println("2. Buscar Producto por ID");
            System.out.println("3. Modificar Datos de un Producto");
            System.out.println("4. Listar Todos los Productos");
            System.out.println("5. Eliminar Productos del Sistema");
            System.out.println("6. Volver");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Mostrando Registro de Nuevo Producto...");
                    productView.createProductMenu(productService, categoryService);
                    break;
                case 2:
                    System.out.println("Busqueda de Producto por ID...");
                    System.out.print("ID a buscar: ");
                    productView.findProductById(productService);
                    break;
                case 3:
                    System.out.println("Mostrando Modificar Producto por ID...");
                    productView.updateProduct(productService);
                    break;
                case 4:
                    System.out.println("Mostrando Listado de Productos...");
                    productView.listAllProducts(productService);
                    break;
                case 5:
                    System.out.println("Eliminar Productos del Sistema...");
                    productView.deleteProduct(productService);
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Menú Admin 2. Gestión de Pedidos
    public void orderMenuAdmin() {
        while (true) {
            System.out.println("\n--- GESTIÓN DE PEDIDOS (ADMIN) ---");
            System.out.println("1. Crear Orden");
            System.out.println("2. Buscar Orden por ID");
            System.out.println("3. Modificar Datos de una Orden");
            System.out.println("4. Listar Todos las Ordenes del Sistema...");
            System.out.println("5. Eliminar una Orden del Sistema");
            System.out.println("6. Volver");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Mostrando Creación de Nueva Orden...");
                    orderView.createOrder();
                    break;
                case 2:
                    System.out.println("Búsqueda de Orden por ID...");
                    System.out.print("ID a buscar: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    // 1. Buscamos la orden usando el servicio
                    Order order = orderService.getOrderById(id).orElse(null);

                    // 2. Verificamos si existe antes de intentar imprimirla
                    if (order != null) {
                        orderView.printOrder(order);
                    } else {
                        System.out.println("No se encontró ninguna orden con el ID " + id);
                    }
                    break;
                case 3:
                    System.out.println("Mostrando Modificar Orden por ID...");
                    int idUpdate = FormValidator.validateInt("Ingrese el ID de la orden a modificar: ");
                    orderView.updateOrder(idUpdate);
                    break;
                case 4:
                    System.out.println("Mostrando Listado de Ordenes del Sistema...");
                    orderView.getAllOrders();
                    break;
                case 5:
                    System.out.println("Eliminar Ordenes del Sistema...");
                    int idDelete = FormValidator.validateInt("Ingrese el ID de la orden a eliminar: ");
                    orderView.deleteOrderById(idDelete);
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Menú Admin 3. Gestión de Ventas
    public void salesMenuAdmin() {
        while (true) {
            System.out.println("\n--- GESTIÓN DE VENTAS (ADMIN) ---");
            System.out.println("1. Histórico de Ventas por Fecha");
            System.out.println("2. Histórico de Ventas por Método de Pago");
            System.out.println("3. Histórico de Ventas por Cliente");
            System.out.println("4. Histórico de Ventas por Categoría");
            System.out.println("5. Histórico de Ventas por Producto");
            System.out.println("6. Volver");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Mostrando Histórico de Ventas por Fecha...");
                    LocalDate date = DateValidator.readDate("Ingrese fecha (dd/MM/yyyy): ");
                    orderService.getOrdersByDate(date).forEach(orderView::printOrder);
                    break;
                case 2:
                    System.out.println("Mostrando Histórico de Ventas por Método de Pago...");
                    PaidMethodEnum method = OrderPaidMethodSelector.PaidMethodSelector();
                    orderService.getOrdersByPaidMethod(method).forEach(orderView::printOrder);
                    break;
                case 3:
                    System.out.println("Mostrando Histórico de Ventas por Cliente...");
                    int cId = FormValidator.validateInt("ID Cliente: ");
                    orderService.getOrdersByCustomer(cId).forEach(orderView::printOrder);
                    break;
                case 4:
                    System.out.println("Mostrando Histórico de Ventas por Categoría...");
                    System.out.println("\nSeleccione Categoría:");
                    System.out.println("1. Torta | 2. Postre | 3. Cupcake | 4. Galleta");
                    int catOption = sc.nextInt();
                    sc.nextLine();

                    Class<? extends Product> categoryClass = null;

                    if (catOption == 1) {
                        categoryClass = Cake.class;
                    } else if (catOption == 2) {
                        categoryClass = Pastry.class;
                    } else if (catOption == 3) {
                        categoryClass = Cupcake.class;
                    } else if (catOption == 4) {
                        categoryClass = Cookie.class;
                    }

                    if (categoryClass == null) {
                        System.out.println("Categoría no válida.");
                    } else {
                        List<Order> filtered = orderService.getOrdersByCategory(categoryClass);
                        if (filtered.isEmpty()) {
                            System.out.println("No se encontraron ventas para esta categoría.");
                        } else {
                            filtered.forEach(orderView::printOrder);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Mostrando Histórico de Ventas por Producto...");
                    int pId = FormValidator.validateInt("ID Producto: ");

                    // Obtenemos todas las órdenes
                    orderService.getAllOrders().stream()
                            // Filtramos las órdenes
                            .filter(o -> o.getItems().stream()
                                    // Forzamos el tipo 'Product p' para asegurar que acceda a getIdProduct()
                                    .anyMatch(p -> ((Product) p).getIdProduct() == pId))
                            .forEach(orderView::printOrder);
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Menú Admin 4. Gestión de Clientes
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
                case 1:
                    System.out.println("Mostrando Registro de Nuevo Cliente...");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Busqueda de Cliente por ID...");
                    System.out.print("ID a buscar: ");
                    customerView.getCustomerById(sc.nextInt());
                    break;
                case 3:
                    System.out.println("Mostrando Modificar Cliente por ID...");
                    customerView.updateCustomer();
                    break;
                case 4:
                    System.out.println("Mostrando Listado de Clientes...");
                    adminView.getAllCustomers();
                    break;
                case 5:
                    System.out.println("Eliminar Clientes del Sistema...");
                    customerView.deleteCustomer();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public void start() {
        showMainMenu();
    }
}