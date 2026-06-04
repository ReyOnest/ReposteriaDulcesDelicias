package cakeshopapp.services;

import cakeshopapp.domain.Product;
import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.services.input.ProductService;
import cakeshopapp.services.outputport.ProductPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductPersistencePort productPersistencePort;

    public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void addProduct(Product product) {
        if (product.getStock() <= 0) {
            product.setState(ProductState.OUT_OF_STOCK);
        } else if (product.getState() == null) {
            product.setState(ProductState.AVAILABLE);
        }
        productPersistencePort.save(product);
        System.out.println("Producto '" + product.getName() + "' registrado exitosamente.");
    }

    @Override
    public void updateProduct(Product product) {
        // El puerto devuelve Optional, lo manejamos así:
        productPersistencePort.findById(product.getIdProduct())
                .ifPresentOrElse(existingProduct -> {
                    productPersistencePort.save(product);
                }, () -> {
                    throw new RuntimeException("No se encontró el producto.");
                });
    }

    @Override
    public List<Product> getAllProducts() {
        return productPersistencePort.findAll();
    }

    @Override
    public List<Product> listAllProducts() {
        return productPersistencePort.findAll();
    }

    @Override
    public List<Product> listAvailableProducts() {
        return productPersistencePort.findAll().stream()
                .filter(p -> p.getState() == ProductState.AVAILABLE && p.getStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductByName(String name) {
        return productPersistencePort.findByName(name);
    }

    // CORRECCIÓN: Delegamos la búsqueda al puerto de persistencia
    @Override
    public Optional<Product> findById(int id) {
        return productPersistencePort.findById(id);
    }

    @Override
    public void deleteProduct(int id) {
        productPersistencePort.deleteById(id);
    }

}