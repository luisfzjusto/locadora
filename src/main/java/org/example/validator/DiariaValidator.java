package org.example.validator;

public class DiariaValidator {

    private static final double DIARIA_MAXIMA = 9999.99;
    public static boolean validarDiaria(double diaria){
        // verifica se o valor é positivo e estabelece o limite de 9999.99
        return diaria > 0 && diaria <= DIARIA_MAXIMA;
    }
}
