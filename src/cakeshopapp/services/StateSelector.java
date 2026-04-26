package cakeshopapp.services;

import cakeshopapp.domain.enums.ProductState;
import java.util.Scanner;

public class StateSelector {
    private Scanner sc = new Scanner(System.in);

    public ProductState selectProductState() {
        System.out.println("--- Seleccione el estado del producto ---");
        System.out.println("1. Disponible");
        System.out.println("2. Agotado");
        System.out.println("3. Por encargo (Pre-Order)");
        System.out.println("4. Descontinuado");
        System.out.print("Opción: ");

        int option = sc.nextInt();

        if (option == 1) {
            return ProductState.AVAILABLE;
        } else if (option == 2) {
            return ProductState.OUT_OF_STOCK;
        } else if (option == 3) {
            return ProductState.PRE_ORDER;
        } else if (option == 4) {
            return ProductState.DISCONTINUED;
        } else {
            System.out.println("Opción no válida. Se asignará 'Disponible' por defecto.");
            return ProductState.AVAILABLE;
        }
    }
}