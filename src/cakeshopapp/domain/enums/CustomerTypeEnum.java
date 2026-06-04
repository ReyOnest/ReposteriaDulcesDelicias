package cakeshopapp.domain.enums;

public enum CustomerTypeEnum {

    NEW_CUSTOMER("Cliente Nuevo"),
    OLD_CUSTOMER("Cliente Antiguo");

    private final String description;

    // Constructor privado para los enums
    CustomerTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}