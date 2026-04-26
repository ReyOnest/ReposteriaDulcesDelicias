package cakeshopapp.services;

import cakeshopapp.domain.Product;
import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.repository.ProductRepository;
import cakeshopapp.view.ProductView;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductView productView;

    public ProductServiceImpl(ProductRepository productRepository, ProductView productView) {
        this.productRepository = productRepository;
        this.productView = productView;
    }

    @Override
    public void addProduct(Product product) {
        if (product.getStock() <= 0) {
            product.setState(ProductState.OUT_OF_STOCK);
        }
        else if (product.getState() == null) {
            product.setState(ProductState.AVAILABLE);
        }

        productRepository.save(product);
        System.out.println("Producto '" + product.getName() + "' registrado exitosamente.");
    }

    @Override
    public void createProduct() {
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Cambiado de List.of() para que devuelva datos reales
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getState() == ProductState.AVAILABLE && p.getStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}