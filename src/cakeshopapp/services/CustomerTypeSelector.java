package cakeshopapp.services;

import cakeshopapp.domain.enums.CustomerTypeEnum;
import cakeshopapp.utils.FormValidator;

public class CustomerTypeSelector {

    public static String selectTypeCustomer(){

        String value = "";

        System.out.println("Seleccione 1. Nuevo 2. Antiguo");

        int option = FormValidator.validateInt("Opcion");

        switch (option){
            case 1:
                value = CustomerTypeEnum.NEW_CUSTOMER.getDescription();
                break;
            case 2:
                value = CustomerTypeEnum.OLD_CUSTOMER.getDescription();
                break;
            default:
                System.out.println("Seleccione una Opción valida");
        }

        return value;
    }

}
