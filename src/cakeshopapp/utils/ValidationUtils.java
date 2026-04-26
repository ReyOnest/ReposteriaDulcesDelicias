package cakeshopapp.utils;

public class ValidationUtils {

    // Valida que el precio sea razonable para un producto de repostería
    public static boolean isPriceValid(double price) {
        return price > 0;
    }

    // Valida el formato del correo del cliente
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Valida que el nombre de la torta no esté vacío
    public static boolean isTextPresent(String text) {
        return text != null && !text.trim().isEmpty();
    }
}