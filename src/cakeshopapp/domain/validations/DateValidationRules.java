package cakeshopapp.domain.validations;

import java.time.LocalDateTime;

public class DateValidationRules {
    public static boolean isPastOrPresent(LocalDateTime date) {
        return date != null && !date.isAfter(LocalDateTime.now());
    }
}