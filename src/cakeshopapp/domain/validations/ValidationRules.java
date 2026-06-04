package cakeshopapp.domain.validations;

import java.util.function.Predicate;

public class ValidationRules {
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isStringValid(String text) {
        return text != null && !text.trim().isEmpty();
    }
    // IDs y números
    public static final Predicate<Integer> POSITIVE_NUMBER = value -> value > 0;
    public static final Predicate<Double> POSITIVE_AMOUNT = value -> value >= 0;

    // Texto
    public static final Predicate<String> MIN_LENGTH_3 = value -> value.length() >= 3;
    public static final Predicate<String> NO_NUMBERS = value -> value.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");

    // Email: contiene @ y un punto después del @
    public static final Predicate<String> VALID_EMAIL =
            value -> value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    // Password: mínimo 8 caracteres, al menos una letra y un número
    public static final Predicate<String> VALID_PASSWORD =
            value -> value.length() >= 8
                    && value.matches(".*[a-zA-Z].*")
                    && value.matches(".*\\d.*");

    // Name: mínimo 3 caracteres, sin incluir números
    public static final Predicate<String> VALID_NAME =
            MIN_LENGTH_3.and(NO_NUMBERS);
}