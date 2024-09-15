package org.example.validator;

public class UFValidator {
    public static boolean validarUF(String uf) {
        // Verifica se a UF é válida (não nula, 2 letras e em maiúsculas)
        return uf != null && uf.toUpperCase().matches("[A-Z]{2}");
    }

    public static String formatarUF(String uf) {
        // Retorna a UF em maiúsculas, se válida
        if (uf != null) {
            return uf.toUpperCase();
        }
        return null;
    }
}
