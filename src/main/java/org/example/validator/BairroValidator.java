package org.example.validator;

public class BairroValidator {
    public static boolean validarBairro(String bairro){
        // verifica se logradouro não contém caracteres especiais e possui, no mínimo, 5 caracteres
        return bairro != null && bairro.trim().length() >= 5 && bairro.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\d\\s]+");
    }
}
