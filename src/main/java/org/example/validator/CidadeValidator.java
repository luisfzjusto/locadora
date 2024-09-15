package org.example.validator;

public class CidadeValidator {
    public static boolean validarCidade(String cidade){
        // verifica se contém apenas letras e o número mínimo de caracteres
        return cidade != null && cidade.trim().length() >= 3 && cidade.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+");
    }
}
