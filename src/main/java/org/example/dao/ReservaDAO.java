package org.example.dao;

import org.example.model.Reserva;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservaDAO { // Classe responsável por acessar dados da tabela reserva no BD

    // Método para inserir uma reserva no BD
    public void reservar(Reserva reserva){
        // Consulta SQL para inserir os dados da reserva
        String sql = "INSERT INTO reserva (id_cliente, id_veiculo, tipo_reserva, quantidade) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection(); // obtém uma conexão com o BD
            PreparedStatement ps = conn.prepareStatement(sql)){ // Prepara a instrução SQL com a conexão obtida

            // Define os parâmetros do PreparedStatement com base nos dados da reserva
            ps.setInt(1, reserva.getCliente().getId()); // associa o ID do cliente (primeiro parâmetro da reserva)
            ps.setInt(2, reserva.getVeiculo().getId()); // associa o ID do veículo (segundo parâmetro da reserva)
            ps.setString(3, reserva.getTipoReserva()); // associa o tipo de reserva (terceiro parâmetro da reserva) e que recebe 3 valores (diaria, mensal, anual)
            ps.setInt(4, reserva.getQuantidade()); // associa a quantidade, que depende do tipo de reserva
            ps.executeUpdate(); // Executa a consulta de inserção no BD
        } catch(SQLException e){ // trata possíveis erros SQL
            e.printStackTrace(); // Exibe erro
        }
    }
}
