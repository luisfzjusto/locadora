package org.example.validator;

public class BairroValidator {
    public static boolean validarBairro(String bairro){
        // Verifica se bairro não contém caracteres especiais, tem pelo menos 5 caracteres e não apenas números
        return bairro != null
                && bairro.trim().length() >= 5
                && bairro.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\d\\s]+") // Permite letras, dígitos e espaços
                && bairro.matches(".*[A-Za-zÀ-ÖØ-öø-ÿ]+.*"); // Garante que tenha ao menos uma letra
    }
}
