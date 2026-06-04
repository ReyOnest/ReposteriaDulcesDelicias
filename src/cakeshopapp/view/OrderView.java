package cakeshopapp.view;

import cakeshopapp.domain.*;
import cakeshopapp.domain.enums.OrderState;
import cakeshopapp.domain.enums.PaidMethodEnum;
import cakeshopapp.services.input.*; // Ajusta según donde tengas tus servicios
import cakeshopapp.services.OrderPaidMethodSelector;
import cakeshopapp.services.OrderStateSelector;
import cakeshopapp.utils.*;
import cakeshopapp.utils.date.DateValidator;
import cakeshopapp.domain.validations.ValidationRules;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {

    private final ProductView productView;
    private final OrderService orderUseCase;
    private final CustomerService customerUseCase;
    private final ProductService productUseCase;
    private final Scanner sc = new Scanner(System.in);

    public OrderView(OrderService orderUseCase, CustomerService customerUseCase, ProductService productUseCase, ProductView productView) {
        this.orderUseCase = orderUseCase;
        this.customerUseCase = customerUseCase;
        this.productUseCase = productUseCase;
        this.productView = productView;
    }
    // Método para personalizar los productos dependiendo del tipo de producto
    private void configurarProducto(Product product) {
        System.out.println("\n--- CONFIGURACIÓN DE: " + product.getName() + " ---");

        if (product instanceof Cake) {
            Cake cake = (Cake) product;
            System.out.println("Seleccione número de porciones: 1. 12 | 2. 20 | 3. 30");
            int op = sc.nextInt();
            cake.setSlices(op == 1 ? 12 : (op == 2 ? 20 : 30));

        } else if (product instanceof Cupcake) {
            Cupcake cup = (Cupcake) product;
            System.out.println("Seleccione tipo de cobertura: 1. Ganache | 2. Crema de Mantequilla | 3. Chantilly");
            int op = sc.nextInt();
            String[] frostings = {"Ganache", "Crema de Mantequilla", "Chantilly"};
            cup.setFrosting(frostings[op - 1]);

        } else if (product instanceof Cookie) {
            Cookie cook = (Cookie) product;
            System.out.println("Seleccione textura: 1. Suave | 2. Crocante");
            int op = sc.nextInt();
            cook.setIsCrunchy(op == 2);

        } else if (product instanceof Pastry) {
            Pastry pastry = (Pastry) product;
            System.out.println("Seleccione tamaño: 1. Pequeño | 2. Mediano | 3. Grande");
            int op = sc.nextInt();
            String[] sizes = {"Pequeño", "Mediano", "Grande"};
            pastry.setSize(sizes[op - 1]);
        }
    }

    public void createOrder() {
        // 1. Recopilamos datos generales
        LocalDate orderDate = DateValidator.readDate("Ingrese la fecha (dd/MM/yyyy): ");
        int customerId = FormValidator.validateInt("Ingrese el id del cliente: ");
        int productId = FormValidator.validateInt("Ingrese el id del producto: ");
        int quantity = FormRuleValidator.readInt("Ingrese la cantidad: ", ValidationRules.POSITIVE_NUMBER, "Debe ser positivo");

        PaidMethodEnum paidMethod = OrderPaidMethodSelector.PaidMethodSelector();
        OrderState orderState = OrderStateSelector.selectOrderState();

        // 2. Buscamos los objetos completos
        Customer customer = customerUseCase.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Product product = productUseCase.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 3. Configurar Producto
        configurarProducto(product);

        // 4. Preparamos los ítems
        List<Product> items = new ArrayList<>();
        items.add(product);

        // 5. Calculamos precio total
        double total = product.getPrice() * quantity;

        // 6. Creamos la orden
        Order newOrder = new Order(0, customer, items, total, orderState, orderDate, paidMethod);

        // 7. Enviamos al servicio
        orderUseCase.createOrder(newOrder);
        System.out.println("Orden creada con éxito.");
    }
    // Método para la creación de una orden por parte del cliente
    public void createOrderForCustomer(Customer customer) {
        System.out.println("\n--- BIENVENIDO " + customer.getName() + ", REALICEMOS TU PEDIDO ---");

        // 1. Mostrar productos disponibles
        productView.listAllProducts(productUseCase);
        int productId = FormValidator.validateInt("Seleccione el ID del producto: ");
        Product product = productUseCase.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productId));

        // 2. Configuración de Producto
        configurarProducto(product);

        // 3. Cantidad y detalles
        int quantity = FormValidator.validateInt("Ingrese la cantidad: ");

        // 4. Selección de pago
        PaidMethodEnum paidMethod = OrderPaidMethodSelector.PaidMethodSelector();

        // 5. Creación del objeto
        double total = product.getPrice() * quantity;
        Order newOrder = new Order(0, customer, List.of(product), total, OrderState.PENDING, LocalDate.now(), paidMethod);

        // 6. Guardado
        orderUseCase.createOrder(newOrder);
        System.out.println("¡Pedido realizado con éxito!");
    }
    // Otros Métodosa
    // 1. Buscar una orden por ID
    public void getOrderById(int orderId) {
        Order order = orderUseCase.getOrderById(orderId).orElse(null);
        if (order != null) {
            printOrder(order);
        } else {
            System.out.println("Error: No se encontró una orden con el ID " + orderId);
        }
    }

    // 2. Ver todas las órdenes (Solo para Admin)
    public void getAllOrders() {
        System.out.println("\n--- LISTADO GENERAL DE ÓRDENES (ADMIN) ---");
        List<Order> orders = orderUseCase.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No hay órdenes registradas en el sistema.");
        } else {
            orders.forEach(this::printOrder);
        }
    }

    // 3. Imprimir orden
    public void printOrder(Order order) {
        System.out.println("\n-------------------------------------------");
        System.out.println("ID Orden: " + order.getOrderId());
        System.out.println("Cliente: " + order.getCustomer().getName() + " " + order.getCustomer().getLastName());
        System.out.println("Fecha: " + order.getOrderDate());
        System.out.println("Total: $" + order.getTotalPrice());
        System.out.println("Estado: " + order.getStatus());
        System.out.println("Método de Pago: " + order.getPaidMethod());
        // Aquí imprimirías detalles de los productos si los necesitas
        System.out.println("-------------------------------------------");
    }

    // 4. Actualizar orden por ID
    public void updateOrder(int orderId) {
        Order order = orderUseCase.getOrderById(orderId).orElse(null);

        if (order == null) {
            System.out.println("Error: Orden no encontrada.");
            return;
        }

        // 1. Mostrar detalles y validar estado (Bloqueante para estados distintos a PENDING)
        System.out.println("\n--- GESTIÓN DE ORDEN ID: " + orderId + " ---");
        printOrder(order);

        if (order.getStatus() != OrderState.PENDING) {
            System.out.println("\n[BLOQUEO]: Su orden está en estado: " + order.getStatus().getDescription());
            System.out.println("Solo se pueden realizar cambios en órdenes PENDIENTES.");
            return;
        }

        // 2. Menú de opciones para PENDIENTE
        System.out.println("1. Cambiar Método de Pago");
        System.out.println("2. Agregar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Cancelar Pedido por completo");
        System.out.print("Seleccione una opción: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                order.setPaidMethod(OrderPaidMethodSelector.PaidMethodSelector());
                break;
            case 2:
                productView.listAllProducts(productUseCase);
                int newPid = FormValidator.validateInt("ID del producto a agregar: ");
                Product newProd = productUseCase.findById(newPid)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + newPid));
                if (newProd != null) {
                    configurarProducto(newProd);
                    order.getItems().add(newProd);
                    recalculateTotal(order);
                }
                break;
            case 3:
                System.out.println("Productos actuales: " + order.getItems());
                int removePid = FormValidator.validateInt("ID del producto a eliminar: ");
                order.getItems().removeIf(p -> p.getIdProduct() == removePid);
                recalculateTotal(order);
                break;
            case 4:
                orderUseCase.deleteOrder(orderId);
                System.out.println("Pedido cancelado y eliminado exitosamente.");
                return; // Salimos ya que la orden no existe más
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // 3. Persistimos los cambios finales
        orderUseCase.updateOrder(order);
        System.out.println("¡Orden actualizada con éxito!");
    }

    // Método auxiliar para mantener limpio el código
    private void recalculateTotal(Order order) {
        double total = order.getItems().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalPrice(total);
    }

    // 5. Eliminar orden por ID
    public void deleteOrderById(int orderId) {
        // Usamos getOrderById y extraemos el valor con .orElse(null)
        Order order = orderUseCase.getOrderById(orderId).orElse(null);

        if (order != null) {
            orderUseCase.deleteOrder(orderId);
            System.out.println("¡La orden " + orderId + " ha sido eliminada con éxito!");
        } else {
            System.out.println("Error: No existe una orden con el ID " + orderId + ".");
        }
    }

    // Método para búsqueda de ordenes por cliente
    public void showOrdersByCustomer(int customerId) {
        System.out.println("\n--- MI HISTORIAL DE PEDIDOS ---");
        List<Order> orders = orderUseCase.getOrdersByCustomer(customerId);

        if (orders.isEmpty()) {
            System.out.println("No tienes pedidos registrados actualmente.");
        } else {
            orders.forEach(this::printOrder);
        }
    }
}