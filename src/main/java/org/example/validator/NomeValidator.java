package org.example.validator;

public class NomeValidator {
    public static boolean validarNome(String nome){
        // verifica se o nome não é nulo e possui no mínimo 10 caracteres, sendo todos letras
        return nome != null && nome.trim().length() >= 10 && nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+");
    }
}
