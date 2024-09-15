package org.example.validator;

public class TelefoneValidator {
    public static boolean validarTelefone(String telefone){
        String regex = "\\(\\d{2}\\) \\d{4,5}-\\d{4}"; // criação da expressão regular que define o padrão aceito de telefones
        return telefone != null && telefone.matches(regex); // verifica se o telefone não é nulo e atende o padrão da regex
    }
}
