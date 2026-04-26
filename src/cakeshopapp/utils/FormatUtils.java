package cakeshopapp.utils;

public class FormatUtils {

    // Método para poner signo de pesos y puntos
    public static String formatCurrency(double amount) {
        return String.format("$ %,.0f", amount);
    }
}
