package cakeshopapp.services;

import cakeshopapp.domain.enums.ProductState;
import cakeshopapp.utils.FormValidator;

public class ProductStateSelector {

    public static String ProductState() {

        System.out.println("Seleccione el estado del producto: ");
        System.out.println("1. Disponible 2. Agotado 3. Por encargo 4. Descontinuado");
        int option = FormValidator.validateInt("Seleccione una opcion: ");

        // 1. Declaramos la variable 'value' antes del switch
        String value = ProductState.AVAILABLE.getDescription(); // Valor por defecto

        switch (option) {
            case 1:
                value = ProductState.AVAILABLE.getDescription();
                break;
            case 2:
                value = ProductState.OUT_OF_STOCK.getDescription();
                break;
            case 3:
                value = ProductState.PRE_ORDER.getDescription();
                break;
            case 4:
                value = ProductState.DISCONTINUED.getDescription();
                break;
            default:
                System.out.println("Opción no válida, se asignará el estado por defecto: Disponible");
        }

        // 2. Retornamos el valor calculado
        return value;
    }
}