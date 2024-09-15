package org.example.validator;

public class NumeroValidator {

    public static boolean validarNumero(String numero){
        // verifica se número possui apenas números
        return numero != null && numero.matches("\\d+");
    }
}
