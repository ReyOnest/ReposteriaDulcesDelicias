package cakeshopapp.domain.enums;

public enum CategoryStateEnum {

    AVAILABLE("Disponible"),
    OUT_OF_STOCK("No Disponible"),
    COMING_SOON("Próximamente");


    private final String  description;

    CategoryStateEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
