package org.example.validator;

public class LogradouroValidator {

    public static boolean validarLogradouro(String logradouro){
        // verifica se logradouro não contém caracteres especiais e possui, no mínimo, 4 caracteres
        return logradouro != null && logradouro.trim().length() >= 4 && logradouro.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\d\\s]+");
    }
}
