package cakeshopapp.services;

import cakeshopapp.domain.Product;
import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void createProduct();
    List<Product> getAllProducts();

    List<Product> listAllProducts();

    List<Product> listAvailableProducts();

    Product findProductByName(String name);
}
