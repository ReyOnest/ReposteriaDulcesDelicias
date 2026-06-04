package cakeshopapp.services;

import cakeshopapp.domain.enums.CustomerState;
import cakeshopapp.utils.FormValidator;

public class CustomerStateSelector {

    public static boolean selectCustomerState(){


        boolean value= false;

        System.out.println("Seleccione 1. Activo 2. Inactivo");

        int option = FormValidator.validateInt("Opcion");

        switch (option){
            case 1:
                value = CustomerState.ACTIVE.getDescription();
                break;
            case 2:
                value = CustomerState.INACTIVE.getDescription();
                break;
            default:
                System.out.println("Seleccione una opcion valida");
        }

        return value;

    }



}
