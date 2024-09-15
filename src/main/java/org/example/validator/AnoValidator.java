package org.example.validator;

public class AnoValidator {
    public static boolean validarAno(String ano){
        // verifica se o ano tem 4 dígitos numéricos
        return ano != null && ano.matches("\\d{4}");
    }
}
