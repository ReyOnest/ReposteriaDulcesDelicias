package cakeshopapp.domain.enums;

public enum OrderState {
    PENDING("Pendiente"),
    IN_PREPARATION("En preparación"),
    READY("Listo"),
    PAID("Pagado"),
    DELIVERED("Entregado"),
    CANCELLED("Cancelado");

    private final String description;

    OrderState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}