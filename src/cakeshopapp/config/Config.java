package cakeshopapp.config;

import cakeshopapp.persistence.repository.*;
import cakeshopapp.services.*;
import cakeshopapp.services.input.*;
import cakeshopapp.services.outputport.*;

public class Config {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    private final AdminService adminService;
    private final CategoryService categoryService;

    public Config() {
        // 1. Inicializar adaptadores de infraestructura
        CustomerPersistencePort customerPort = new CustomerRepositoryAdapterMySql();
        ProductPersistencePort productPort = new ProductRepositoryAdapterMySql();
        OrderPersistencePort orderPort = new OrderRepositoryAdapterMySql();
        AdminPersistencePort adminPort = new AdminRepositoryAdapterMySql();
        CategoryPersistencePort categoryPort = new CategoryRepositoryAdapterMySql();

        // 2. Acoplar servicios inyectando sus respectivos puertos
        this.customerService = new CustomerServiceImpl(customerPort);
        this.productService = new ProductServiceImpl(productPort);
        this.orderService = new OrderServiceImpl(orderPort);
        this.adminService = new AdminServiceImpl(adminPort);
        this.categoryService = new CategoryServiceImpl(categoryPort);

    }
    // 3. Getters
    public CustomerService getCustomerService() {
        return customerService;
    }
    public ProductService getProductService() {
        return productService;
    }
    public OrderService getOrderService() {
        return orderService;
    }
    public AdminService getAdminService() {
        return adminService;
    }
    public CategoryService getCategoryService() { return categoryService; }

}