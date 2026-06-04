package cakeshopapp.services.outputport;

import cakeshopapp.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    void save(Product product);
    List<Product> findAll();
    Optional<Product> findById(int id);
    Product findByName(String name);
    void deleteById(int id);
}