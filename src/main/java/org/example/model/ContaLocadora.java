package org.example.model;

import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class ContaLocadora {

    // Essa classe representa a conta da loja. Ela mantém o saldo e o atualiza no BD

    private int id; // id fixo que será definido no construtor
    private double saldo; // saldo atual da loja

    // Construtor que define o id fixo da conta da loja e carrega o saldo a partir do BD
    public ContaLocadora() {
        this.id = 1; // ID fixo para a conta já que só ela será utilizada
        this.saldo = carregarSaldo(); // carrega o saldo inincial
    }

    // Método para carregar o saldo da conta a partir do banco de dados.
    // Como apenas uma conta será manipulada, a lógica de persistência no BD está na própria model
    // ao invés de uma classe específica no pacote DAO.
    private double carregarSaldo(){
        String sql = "SELECT saldo FROM conta_locadora WHERE id = ?"; // consulta SQL para buscar o saldo
        try (Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
             PreparedStatement ps = conn.prepareStatement(sql)) { // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
            ps.setInt(1, this.id); // Define o id fixo da conta no parâmetro (sql)
            ResultSet rs = ps.executeQuery(); // Executa a consulta
            if (rs.next()) { // se a consulta retorna algum resultado
                return rs.getDouble("saldo"); // Retorna o saldo (valor que consta na coluna saldo)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // imprime erros de SQL, caso aconteça alguma exceção durante a execução
        }
        return 0.0; // retorna saldo 0.0 caso haja algum erro
    }

    // Nesse contexto, apenas o método getSaldo é necessário
    public double getSaldo() {
        return saldo;
    }

    // Adiciona um valor ao saldo e atualiza no BD
    public void adicionarSaldo(double valor){
        this.saldo += valor; // Incrementa o saldo com o valor recebido
        atualizarSaldo(); // Atualiza o saldo no BD
    }

    // Atualiza o saldo no BD
    private void atualizarSaldo(){
        String sql = "UPDATE conta_locadora SET saldo = ? WHERE id = ?"; // consulta SQL para atualizar o saldo
        try (Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD através de um método estático (getConnectin da classe DatabaseConnection)
             PreparedStatement ps = conn.prepareStatement(sql)) { // cria um objeto do tipo PreparedStatement, que é utilizado para executar consultas SQL. O comando SQL que será executado é passado no parâmetro (no caso, String sql)
            ps.setDouble(1, this.saldo); // define o saldo no parâmetro (sql)
            ps.setInt(2, this.id); // define o id da conta (no caso, id fixo)
            ps.executeUpdate(); // executa a atualização no BD
        } catch (SQLException e) {
            e.printStackTrace(); // imprime erros de SQL, caso aconteça alguma exceção durante a execução
        }
    }
}
