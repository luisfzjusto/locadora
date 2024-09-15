package org.example.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataNascimentoValidator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean validarFormatoData(String dataNascimentoStr) {
        try {
            // Tenta converter a string para LocalDate usando o formato definido
            LocalDate.parse(dataNascimentoStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            // Se ocorrer uma exceção de formatação, a data é inválida
            return false;
        }
    }

    public static boolean validarMaioridade(LocalDate dataNascimento) {
        try {
            // Verifica se o cliente tem pelo menos 18 anos
            return dataNascimento != null && Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

