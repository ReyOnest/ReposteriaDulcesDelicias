package cakeshopapp;

import cakeshopapp.repository.OrderRepository;
import cakeshopapp.repository.CustomerRepository;
import cakeshopapp.repository.ProductRepository;
import cakeshopapp.services.OrderService;
import cakeshopapp.services.OrderServiceImpl;
import cakeshopapp.services.ProductService;
import cakeshopapp.services.ProductServiceImpl;
import cakeshopapp.userinterface.MenuApp;
import cakeshopapp.view.AdminView;
import cakeshopapp.view.CustomerView;
import cakeshopapp.view.ProductView;

public class Main {

    public static void main(String[] args) {
        // Instanciamos los Repositorios
        CustomerRepository customerRepo = new CustomerRepository();
        ProductRepository productRepo = new ProductRepository();
        OrderRepository orderRepo = new OrderRepository();

        // Instanciamos las Vistas de apoyo
        ProductView productView = new ProductView();

        // Instanciamos los Servicios
        ProductService productService = new ProductServiceImpl(productRepo, productView);
        OrderService orderService = new OrderServiceImpl(orderRepo);

        // Instanciamos las Vistas principales
        CustomerView customerView = new CustomerView(customerRepo);
        AdminView adminView = new AdminView(customerRepo);

        // Parámetros para constructor de MenuApp
        MenuApp app = new MenuApp(customerView, adminView, productView, productService);

        // Iniciamos la ejecución
        app.showMainMenu();
    }
}