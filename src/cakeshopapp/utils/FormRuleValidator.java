package cakeshopapp.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class FormRuleValidator {

    private static final Scanner sc = new Scanner(System.in);

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                sc.nextLine();
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                double value = sc.nextDouble();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número decimal.");
                sc.nextLine();
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                boolean value = sc.nextBoolean();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese true o false.");
                sc.nextLine();
            }
        }
    }

    public static String readString(String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("El campo no puede estar vacío.");
        }
    }



    //Reglas de Negocio


    // Recibe el prompt, un Predicate con la regla, y el mensaje de error
    public static String readString(String prompt, Predicate<String> rule, String errorMessage) {
        while (true) {
            String value = readString(prompt);
            if (rule.test(value)) {
                return value;
            }
            System.out.println(errorMessage);
        }
    }

    public static int readInt(String prompt, Predicate<Integer> rule, String errorMessage) {
        while (true) {
            int value = readInt(prompt);
            if (rule.test(value)) {
                return value;
            }
            System.out.println(errorMessage);
        }
    }

    public static double readDouble(String prompt, Predicate<Double> rule, String errorMessage) {
        while (true) {
            double value = readDouble(prompt);
            if (rule.test(value)) {
                return value;
            }
            System.out.println(errorMessage);
        }
    }

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