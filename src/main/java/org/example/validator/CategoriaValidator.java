package org.example.validator;

import org.example.enums.Categoria;

public class CategoriaValidator {
    public static boolean validarCategoria(String categoria){
        try{
            Categoria.valueOf(categoria.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
