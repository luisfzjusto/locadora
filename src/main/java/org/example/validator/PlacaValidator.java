package org.example.validator;

public class PlacaValidator {
    public static boolean validarPlaca(String placa){
        // verifica se placa atende os critérios AAA-0000 ou AAA-0A00
        return placa != null && (placa.matches("[A-Z]{3}-\\d{4}") || placa.matches("[A-Z]{3}-\\d[A-Z]\\d{2}"));
    }
}
