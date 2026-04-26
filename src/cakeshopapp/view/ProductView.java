package cakeshopapp.view;

import cakeshopapp.domain.Cake;
import cakeshopapp.domain.Pastry;
import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.services.ProductService;
import java.util.Scanner;

public class ProductView {

    private final Scanner sc = new Scanner(System.in);

    public void createProductMenu(ProductService productService) {
        System.out.println("\n--- REGISTRO DE NUEVO PRODUCTO ---");
        System.out.println("¿Qué tipo de producto desea agregar?");
        System.out.println("1. Torta (Cake)");
        System.out.println("2. Postre (Pastry)");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el ID del producto: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el Nombre del producto: ");
        String name = sc.nextLine();

        System.out.print("Ingrese el Precio del Producto: ");
        double price = sc.nextDouble();

        System.out.print("Cantidad en inventario: ");
        int stock = sc.nextInt();
        sc.nextLine();

        if (type == 1) {
            System.out.print("Sabor de la torta (Vainilla, Chocolate, etc.): ");
            String flavor = sc.nextLine();
            System.out.print("Tamaño de la torta (12, 20 o 30 porciones): ");
            String slicesInput = sc.nextLine();

            Cake newCake = new Cake(id, name, flavor, price, stock, ProductState.AVAILABLE);
            newCake.setFlavor(flavor);
            newCake.setSlices(Integer.parseInt(slicesInput)); // Asegúrate de tener este método en Cake o Product

            productService.addProduct(newCake);
            System.out.println("\n¡Torta de " + flavor + " (" + slicesInput + " porciones) registrada!");
        } else {
            System.out.print("Sabor del postre: ");
            String flavor = sc.nextLine();
            System.out.print("Tamaño/Porción (Pequeño/Mediano/Grande): ");
            String size = sc.nextLine();

            Pastry newPastry = new Pastry(id, name, flavor, price, stock, ProductState.AVAILABLE);
            newPastry.setFlavor(flavor); // Asegúrate de tener este método en Pastry o Product
            newPastry.setSize(size);

            productService.addProduct(newPastry);
            System.out.println("\n¡Postre de " + flavor + " (" + size + ") registrado!");
        }
    }

    public void listAllProducts(ProductService productService) {
        System.out.println("\n--- INVENTARIO DE DULCES DELICIAS ---");
        productService.listAllProducts().forEach(System.out::println);
    }
}