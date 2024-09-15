package org.example.validator;

public class MarcaValidator {

    public static boolean validarMarca(String marca){
        // verifica se a marca tem, no mínimo 3 caracteres e se são todos letras
        return marca != null && marca.trim().length() >= 3 && marca.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+");
    }
}
