package org.example.validator;

public class ModeloValidator {
    public static boolean validarModelo(String modelo){
        // verifica se modelo tem, no mínimo, 3 caracteres e permite letras e números
        return modelo != null && modelo.trim().length() >= 2 && modelo.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\d\\s]+");
    }
}
