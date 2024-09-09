package org.example.dao;

import org.example.model.Endereco;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Define a classe EnderecoDAO, responsável por acessar os dados da tabela endereco no BD
public class EnderecoDAO {

    // Método para inserir um novo endereço no BD e retornar o ID gerado
    public int inserirEndereco(Endereco endereco){
        // Consulta SQL para inserir endereço e retornar o ID gerado
        String sql = "INSERT INTO endereco(logradouro, numero, bairro, cidade, uf, cep) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection(); // obtém a conexão com o BD
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){ // Prepara a consulta SQL e indica que deve retornar a chave gerada (ID)
            // Preenche os parâmetros da consulta com os valores do objeto do tipo Endereco
            ps.setString(1, endereco.getLogradouro()); // define o valor para o campo logradouro
            ps.setString(2, endereco.getNumero()); // define o valor para o campo numero
            ps.setString(3, endereco.getBairro()); // define o valor para o campo bairro
            ps.setString(4, endereco.getCidade()); // define o valor para o campo cidade
            ps.setString(5, endereco.getUf()); // define o valor para o campo UF
            ps.setString(6, endereco.getCep()); // define o valor para o campo CEP
            ps.executeUpdate(); // Executa a consulta SQL de inserção

            ResultSet rs = ps.getGeneratedKeys(); // obtém o resultado com a chave gerada (do endereco recém criado)
            if(rs.next()){ // se há um resultado
                return rs.getInt(1); // retorna o ID gerado para o endereço
            }
        } catch(SQLException e){ // tratamento das exceções SQL
            e.printStackTrace(); // Exibição do erro
        }
        return -1; // retorna -1 se ocorrer algum erro durante a inserção.
    }
}
