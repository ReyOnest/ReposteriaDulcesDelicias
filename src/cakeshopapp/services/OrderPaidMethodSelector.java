package cakeshopapp.services;

import cakeshopapp.domain.enums.PaidMethodEnum;
import cakeshopapp.utils.FormValidator;

public class OrderPaidMethodSelector {

    public static PaidMethodEnum PaidMethodSelector(){

        System.out.println("Selecione el metodo de pago: ");
        System.out.println("1. Efectivo 2. Tarjeta de Crédito 3. Transferencia 4. Tarjeta Débito");

        int option = FormValidator.validateInt("Seleccione una opción: ");

        // Devolvemos directamente el Enum
        return switch (option) {
            case 1 -> PaidMethodEnum.CASH;
            case 2 -> PaidMethodEnum.CREDIT_CARD;
            case 3 -> PaidMethodEnum.TRANSFER;
            case 4 -> PaidMethodEnum.DEBIT_CARD;
            default -> {
                System.out.println("Opción no válida, se asignará efectivo por defecto.");
                yield PaidMethodEnum.CASH; // Valor de respaldo
            }
        };
    }
}