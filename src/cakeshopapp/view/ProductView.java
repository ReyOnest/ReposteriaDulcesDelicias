package cakeshopapp.view;

import cakeshopapp.domain.*;
import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.services.input.CategoryService;
import cakeshopapp.services.input.ProductService;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ProductView {

    private final Scanner sc = new Scanner(System.in);

    public void createProductMenu(ProductService productService, CategoryService categoryService) {
        System.out.println("\n--- REGISTRO DE NUEVO PRODUCTO ---");

        // 1. Unificamos: La categoría determina el tipo de producto
        System.out.println("Seleccione la categoría del producto:");
        List<Category> categories = categoryService.getAllCategories();

        // Verificamos que haya categorías
        if (categories.isEmpty()) {
            System.out.println("Error: No hay categorías registradas en el sistema.");
            return;
        }

        categories.forEach(c -> System.out.println(c.getIdCategory() + ". " + c.getNameCategory()));
        System.out.print("Seleccione: ");

        int catId = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        Category selectedCategory = categoryService.getCategoryById(catId).orElse(null);

        if (selectedCategory == null) {
            System.out.println("Error: Categoría no encontrada. Operación cancelada.");
            return;
        }

        // 2. Captura datos comunes
        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Precio: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        System.out.print("Sabor: ");
        String flavor = sc.nextLine();

        // 3. Lógica por Tipo de Categoría
        String catName = selectedCategory.getNameCategory().toUpperCase();

        try {
            if (catName.contains("TORTA") || catName.contains("CAKE")) {
                System.out.print("Porciones: ");
                int slices = sc.nextInt();
                Cake cake = new Cake(0, name, flavor, price, stock, ProductState.AVAILABLE, selectedCategory);
                cake.setSlices(slices);
                productService.addProduct(cake);

            } else if (catName.contains("POSTRE") || catName.contains("PASTRY")) {
                System.out.print("Tamaño (P/M/G): ");
                String size = sc.nextLine();
                Pastry pastry = new Pastry(0, name, flavor, price, stock, ProductState.AVAILABLE, selectedCategory);
                pastry.setSize(size);
                productService.addProduct(pastry);

            } else if (catName.contains("CUPCAKE")) {
                System.out.print("Cobertura: ");
                String frosting = sc.nextLine();
                Cupcake cupcake = new Cupcake(0, name, flavor, price, stock, ProductState.AVAILABLE, frosting, selectedCategory);
                productService.addProduct(cupcake);

            } else if (catName.contains("GALLETA") || catName.contains("COOKIE")) {
                System.out.print("¿Es crujiente? (true/false): ");
                boolean isCrunchy = sc.nextBoolean();
                Cookie cookie = new Cookie(0, name, flavor, price, stock, ProductState.AVAILABLE, isCrunchy, selectedCategory);
                productService.addProduct(cookie);

            } else {
                System.out.println("Categoría no mapeada a un tipo de producto específico.");
                return;
            }
            System.out.println("¡Producto registrado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al procesar los datos específicos: " + e.getMessage());
        }
    }

    public void listAllProducts(ProductService productService) {
        System.out.println("\n--- INVENTARIO ---");
        productService.listAllProducts().forEach(p -> System.out.println(p.toString()));
    }

    public void findProductById(ProductService productService) {
        System.out.print("Ingrese ID del producto a buscar: ");
        int id = sc.nextInt();
        // CORRECCIÓN: Manejo de Optional en lugar de comparación con null
        productService.findById(id).ifPresentOrElse(
                product -> System.out.println("Producto encontrado: " + product),
                () -> System.out.println("Error: Producto no encontrado.")
        );
    }

    public void updateProduct(ProductService productService) {
        System.out.print("Ingrese ID del producto a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        productService.findById(id).ifPresentOrElse(product -> {
            System.out.print("Nuevo nombre (actual: " + product.getName() + "): ");
            product.setName(sc.nextLine());
            System.out.print("Nuevo precio: ");
            product.setPrice(sc.nextDouble());

            productService.updateProduct(product);
            System.out.println("¡Producto actualizado exitosamente!");
        }, () -> {

            System.out.println("Error: Producto no encontrado.");
        });
    }

    public void deleteProduct(ProductService productService) {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = sc.nextInt();

        productService.findById(id).ifPresentOrElse(
                product -> {
                    productService.deleteProduct(id);
                    System.out.println("Proceso de eliminación ejecutado.");
                },
                () -> System.out.println("Error: No se puede eliminar, el producto no existe.")
        );
    }
}