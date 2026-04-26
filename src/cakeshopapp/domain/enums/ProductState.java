package cakeshopapp.domain.enums;

public enum ProductState {
    AVAILABLE ("Disponible"),
    OUT_OF_STOCK ("Agotado"),
    PRE_ORDER ("Por encargo"),
    DISCONTINUED ("Descontinuado");

    private final String description;

    ProductState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
