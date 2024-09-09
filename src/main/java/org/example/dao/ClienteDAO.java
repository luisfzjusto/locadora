package org.example.dao;

import org.example.model.Cliente;
import org.example.model.Endereco;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO { // Classe responsável por acessar dados da tabela cliente no BD

    // Método para inserir um cliente no BD
    public void inserirCliente(Cliente cliente){
        // Consulta SQL para inserir um cliente e retornar o ID gerado automaticamente
        String sql = "INSERT INTO cliente (nome, cpf, data_nascimento, telefone, id_endereco) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try(Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
            PreparedStatement ps = conn.prepareStatement(sql)){ // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
            ps.setString(1, cliente.getNome()); // define o valor para o campo nome
            ps.setString(2, cliente.getCpf()); // define o valor para o campo cpf
            ps.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento())); // define o valor para o campo data_nascimento
            ps.setString(4, cliente.getTelefone()); // define o valor para o campo telefone
            ps.setInt(5, cliente.getEndereco().getId()); // define o valor para o campo id_endereco, que referencia a tabela de endereco

            // executa a consulta e armazena o resultado que contém o ID gerado para o cliente
            ResultSet rs = ps.executeQuery();
            if(rs.next()){ // verifica se há um resultado (um ID gerado)
                cliente.setId(rs.getInt("id")); // atribui o ID gerado ao objeto cliente
            }
        } catch (SQLException e){ // Trata eventuais exceções SQL
            e.printStackTrace(); // Exibe o erro
        }
    }

    // Método para buscar um cliente pelo CPF no BD
    public Cliente buscarClientePorCPF(String cpf) {
        // Consulta SQL para buscar um cliente com base no CPF
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
             PreparedStatement ps = conn.prepareStatement(sql)) { // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
            ps.setString(1, cpf); // define o CPF como parâmetro na consulta SQL
            ResultSet rs = ps.executeQuery(); // Executa a consulta e armazena o resultado
            if (rs.next()) { // verifica se há um resultado (se o cliente foi encontrado)
                Cliente cliente = new Cliente(); // Cria um objeto cliente para armazenar os dados
                cliente.setId(rs.getInt("id")); // Define o ID do cliente
                cliente.setNome(rs.getString("nome")); // Define o nome do cliente
                cliente.setCpf(rs.getString("cpf")); // define o cpf do cliente
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate()); // Define a data de nascimento
                cliente.setTelefone(rs.getString("telefone")); // define o telefone do cliente

                int idEndereco = rs.getInt("id_endereco"); // obtém o ID do endereço associado ao cliente

                Endereco endereco = buscarEnderecoPorId(idEndereco); // Chama o método buscarEnderecoPorId para obter o endereço completo

                cliente.setEndereco(endereco); // define o endereço no objeto cliente

                return cliente; // retorna o cliente encontrado
            }
        } catch (SQLException e) { // tratamento de possíveis exceções SQL
            e.printStackTrace(); // exibição do erro
        }
        return null; // se o cliente não for encontrado, retorna null
    }

    // Método para listar todos os clientes do BD
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>(); // Criação de uma lista para armazenar os clientes
        // Consulta SQL para buscar todos os clientes
        String sql = "SELECT * FROM cliente";
        try (Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
             PreparedStatement ps = conn.prepareStatement(sql); // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
             ResultSet rs = ps.executeQuery()) { // Executa a consulta e armazena o resultado

            while (rs.next()) { // Iteração sobre o ResultSet, criando um objeto cliente para cada registro encontrado
                Cliente cliente = new Cliente(); // cria o objeto cliente
                cliente.setId(rs.getInt("id")); // define o id do cliente
                cliente.setNome(rs.getString("nome")); // define o nome
                cliente.setCpf(rs.getString("cpf")); // define o cpf
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate()); // // define a data de nascimento
                cliente.setTelefone(rs.getString("telefone")); // define o telefone

                int idEndereco = rs.getInt("id_endereco"); // obtém o ID do endereço associado ao cliente

                Endereco endereco = buscarEnderecoPorId(idEndereco); // chama o método que busca o endereço pelo id e retorna o endereço completo

                cliente.setEndereco(endereco); // define o endereço no objeto cliente

                clientes.add(cliente); // adiciona o cliente à lista
            }
        } catch (SQLException e) { // trata possíveis exceções SQL
            e.printStackTrace(); // Exibe o erro
        }
        return clientes; // retorna a lista de clientes
    }

    // Método PRIVADO para buscar um endereço no BD através do ID
    private Endereco buscarEnderecoPorId(int idEndereco) {
        // Consulta SQL para buscar endereço por id
        String sql = "SELECT * FROM endereco WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
             PreparedStatement ps = conn.prepareStatement(sql)) { // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
            ps.setInt(1, idEndereco); // define o ID do endereço como parâmetro na consulta
            ResultSet rs = ps.executeQuery(); // Executa a consulta e armazena o resultado
            if (rs.next()) { // Verifica se o endereço foi encontrado
                Endereco endereco = new Endereco(); // Caso positivo, cria um objeto do tipo Endereco
                endereco.setId(rs.getInt("id")); // Define o ID do endereco
                endereco.setLogradouro(rs.getString("logradouro")); // Define o logradouro do endereco
                endereco.setNumero(rs.getString("numero")); // Define o número do endereco
                endereco.setBairro(rs.getString("bairro")); // Define o bairro do endereco
                endereco.setCidade(rs.getString("cidade")); // Define a cidade do endereco
                endereco.setUf(rs.getString("uf")); // Define a UF do endereco
                endereco.setCep(rs.getString("cep")); // Define o CEP do enderecp
                return endereco; // Retorna o objeto endereco
            }
        } catch (SQLException e){ // Trata as exceções SQL
            e.printStackTrace(); // Exibe o erro
        }
        return null; // se o endereço não foi encontrado, retorna null
    }
}
