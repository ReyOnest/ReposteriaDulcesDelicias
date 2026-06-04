package cakeshopapp.services;

import cakeshopapp.domain.enums.OrderState;
import cakeshopapp.utils.FormValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderStateSelector {

    // Define qué estados son válidos como siguientes pasos
    public List<OrderState> getNextStates(OrderState currentState) {
        List<OrderState> nextStates = new ArrayList<>();

        switch (currentState) {
            case PENDING:
                nextStates.add(OrderState.PAID);
                nextStates.add(OrderState.CANCELLED);
                break;
            case PAID:
                nextStates.add(OrderState.DELIVERED);
                break;
            case CANCELLED:
            case DELIVERED:
                // No hay estados siguientes para pedidos finalizados
                break;
        }
        return nextStates;
    }

    // Método Selector de Estado de la Orden
    public static OrderState selectOrderState() {
        System.out.println("Seleccione el estado de la orden:");
        OrderState[] states = OrderState.values();
        for (int i = 0; i < states.length; i++) {
            System.out.println((i + 1) + ". " + states[i].getDescription());
        }

        int option = FormValidator.validateInt("Seleccione una opción: ");
        if (option > 0 && option <= states.length) {
            return states[option - 1];
        }
        System.out.println("Opción inválida. Asignando PENDING por defecto.");
        return OrderState.PENDING;
    }
}