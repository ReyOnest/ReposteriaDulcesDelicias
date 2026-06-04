package cakeshopapp.services.input;

import cakeshopapp.domain.Product;
import java.util.List;
import java.util.Optional; // Importación correcta para manejar resultados nulos de forma segura

public interface ProductService {
    void addProduct(Product product);
    List<Product> getAllProducts();
    List<Product> listAllProducts();
    List<Product> listAvailableProducts();
    Product findProductByName(String name);
    void updateProduct(Product product);
    Optional<Product> findById(int id);
    void deleteProduct(int id); // Asegúrate de tener este método
}