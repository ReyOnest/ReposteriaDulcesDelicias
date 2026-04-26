package cakeshopapp.repository;

import cakeshopapp.domain.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getIdProduct() == id)
                .findFirst()
                .orElse(null);
    }

    public Product findByName(String productName) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public Product deleteById(int id) {
        products.removeIf(p -> p.getIdProduct() == id);
        return products.remove(id);
    }
}