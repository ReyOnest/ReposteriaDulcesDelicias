package cakeshopapp.domain.enums;

public enum PaidMethodEnum {
    CASH("Efectivo"),
    CREDIT_CARD("Tarjeta de Crédito"),
    TRANSFER("Transferencia"),
    DEBIT_CARD("Tarjeta Debito");

    private final String description;

    PaidMethodEnum(String description){
        this.description = description;

    }

    public String getDescription(){
        return description;
    }

    }