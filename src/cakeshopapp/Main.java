package cakeshopapp;

import cakeshopapp.config.Config;
import cakeshopapp.services.input.ProductService;
import cakeshopapp.userinterface.MenuApp;
import cakeshopapp.view.AdminView;
import cakeshopapp.view.CustomerView;
import cakeshopapp.view.OrderView;
import cakeshopapp.view.ProductView;

public class Main {

    public static void main(String[] args) {
        // 1. Inicializamos la configuración hexagonal
        Config config = new Config();

        // 2. Instanciamos las vistas
        ProductView productView = new ProductView();
        CustomerView customerView = new CustomerView(config.getCustomerService());
        AdminView adminView = new AdminView(config.getAdminService(), config.getCustomerService());

        // Instanciamos el OrderView con todas sus dependencias necesarias
        OrderView orderView = new OrderView(
                config.getOrderService(),
                config.getCustomerService(),
                config.getProductService(),
                productView
        );

        // 3. Obtenemos el servicio de productos
        ProductService productService = config.getProductService();

        // 4. Acoplamos la interfaz de consola principal
        MenuApp app = new MenuApp(
                customerView,
                adminView,
                productView,
                orderView,
                productService,
                config.getOrderService(),
                config.getCategoryService(),
                config.getCustomerService()
        );

        // 5. Encendemos el sistema
        app.showMainMenu();
    }
}