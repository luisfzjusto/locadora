package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Define a classe DatabaseConnection. Essa classe fornece um método utilitário para se conectar a um banco de dados
public class DatabaseConnection {


    private static final String URL = "jdbc:postgresql://localhost:5432/locadora"; // Define a URL do banco de
    // dados como uma constante (static final). A URL segue o padrão JDBC:
    // - jdbc:postgresql://: protocolo de conexão ao PostgreSQL.
    // - localhost:5432: define que o banco de dados está rodando localmente na porta padrão 5432 do PostgreSQL.
    // - /locadora: nome do banco de dados ao qual se conectar
    private static final String USER = "postgres"; // Define o nome de usuário (USER) para autenticação
    // no banco de dados como uma constante
    private static final String PASSWORD = "admin"; // Define a senha (PASSWORD) para o usuário que será
    // utilizada na autenticação do banco de dados

    public static Connection getConnection() throws SQLException { // Declara o método getConnection como estático
        // Ele retorna um objeto do tipo Connection, que representa uma conexão ativa com o banco de dados
        // O método também especifica que o método pode lançar uma SQLException caso ocorra algum erro ao tentar
        // estabelecer a conexão

        return DriverManager.getConnection(URL, USER, PASSWORD); // Usa a classe DriverManager para estabelecer
        // a conexão com o banco de dados PostgreSQL
        // Passa como parâmetros a URL, o nome de usuário (USER) e a senha (PASSWORD)
        // Se a conexão for bem-sucedida, um objeto Connection será retornado. Esse objeto é utilizado para
        // interagir com o banco de dados
    }
}
