package cakeshopapp.services;

import cakeshopapp.domain.enums.CategoryStateEnum;
import cakeshopapp.utils.FormValidator;

public class CategoryStateSelector {

    public static CategoryStateEnum selectCategoryState() { // Devuelve el Enum, no un String
        System.out.println("\n--- SELECCIÓN DE ESTADO ---");
        System.out.println("1. Disponible");
        System.out.println("2. No disponible");
        System.out.println("3. Próximamente");

        int option = FormValidator.validateInt("Seleccione una opción: ");

        return switch (option) { // Usamos switch-expression (Java 14+)
            case 1 -> CategoryStateEnum.AVAILABLE;
            case 2 -> CategoryStateEnum.OUT_OF_STOCK;
            case 3 -> CategoryStateEnum.COMING_SOON;
            default -> {
                System.out.println("Opción no válida, se asignará estado por defecto.");
                yield CategoryStateEnum.OUT_OF_STOCK; // Valor de respaldo seguro
            }
        };
    }
}