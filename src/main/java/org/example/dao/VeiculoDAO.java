package org.example.dao;

import org.example.model.Veiculo;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO { // Classe responsável por acessar dados da tabela veiculo no BD

    // Método para inserir um veículo no BD
    public void inserirVeiculo(Veiculo veiculo){
        // Consulta SQL para inserir um novo veículo e retornar o ID gerado
        String sql = "INSERT INTO veiculo (marca, modelo, ano, placa, preco_por_dia, categoria, status) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try(Connection conn = DatabaseConnection.getConnection(); // Estabelece uma conexão com o banco de dados usando o método getConnection da classe DatabaseConnection
            PreparedStatement ps = conn.prepareStatement(sql)){ // Prepara a consulta SQL para execução no banco de dados, vinculando-a à conexão.
            ps.setString(1, veiculo.getMarca()); // Define a marca do veículo
            ps.setString(2, veiculo.getModelo()); // Define o modelo do veículo
            ps.setInt(3, veiculo.getAno()); // Define o ano de fabricação do veículo
            ps.setString(4, veiculo.getPlaca()); // Define a placa do veículo
            ps.setDouble(5, veiculo.getDiaria()); // Define o valor da diária do veículo
            ps.setString(6, veiculo.getCategoria()); // Define a categoria do veículo
            ps.setString(7, veiculo.getStatus()); // Define o status do veículo

            ResultSet rs = ps.executeQuery(); // Executa a consulta SQL e recebe o resultado, incluindo o ID gerado pelo BD
            if(rs.next()){ // verifica se há um resultado válido
                veiculo.setId(rs.getInt("id")); // caso positivo, obtém o ID gerado pelo BD e atribui ao objeto veiculo
            }
        } catch(SQLException e){ // caso ocorra um erro durante a execução da consulta SQL, será capturado aqui
            e.printStackTrace(); // Imprime o erro no console
        }
    }

    // Método para buscar um veículo no BD com base na placa
    public Veiculo buscarVeiculoPorPlaca(String placa){
        // Declara a consulta SQL que busca o veículo pela placa fornecida
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        try(Connection conn = DatabaseConnection.getConnection(); // Estabelece uma conexão com o banco de dados usando o método getConnection da classe DatabaseConnection
        PreparedStatement ps = conn.prepareStatement(sql)){ // Prepara a consulta SQL para execução no banco de dados, vinculando-a à conexão.
            ps.setString(1, placa); // Define a placa
            ResultSet rs = ps.executeQuery(); // Executa a consulta e armazena os resultados no ResultSet
            if(rs.next()){ // Verifica se foi encontrado um veículo com a placa fornecida (rs.next() verifica se há resultados)
                Veiculo veiculo = new Veiculo(); // caso positivo, cria um novo objeto Veiculo para armazenar os dados retornados do banco de dados
                veiculo.setId(rs.getInt("id")); // Atribui o ID do veículo retornado do banco ao objeto veiculo.
                veiculo.setMarca(rs.getString("marca")); // Atribui a marca do veículo retornado do banco ao objeto veiculo.
                veiculo.setModelo(rs.getString("modelo")); // Atribui o modelo do veículo retornado do banco ao objeto veiculo.
                veiculo.setAno(rs.getInt("ano")); // Atribui o ano de fabricação do veículo retornado do banco ao objeto veiculo.
                veiculo.setPlaca(rs.getString("placa")); // Atribui a placa do veículo retornado do banco ao objeto veiculo.
                veiculo.setDiaria(rs.getDouble("preco_por_dia")); // Atribui a diária do veículo retornado do banco ao objeto veiculo.
                veiculo.setCategoria(rs.getString("categoria")); // Atribui a categoria do veículo retornado do banco ao objeto veiculo.
                veiculo.setStatus(rs.getString("status")); // Atribui o status do veículo retornado do banco ao objeto veiculo.
                return veiculo; // Retorna o objeto veiculo preenchido com os dados do banco.
            }
        }catch(SQLException e){ // caso ocorra um erro durante a execução da consulta SQL, será capturado aqui
            e.printStackTrace(); // Imprime o erro no console
        }
        return null; // Se nenhum veículo for encontrado com a placa fornecida, retorna null
    }

    // Método para listar todos os veículos no banco de dados
    public List<Veiculo> listarVeiculos(){
        List<Veiculo> veiculos = new ArrayList<>(); // Cria uma lista para armazenar todos os veículos encontrados no banco
        // Declara a consulta SQL que busca todos os veículos
        String sql = "SELECT * FROM veiculo";
        try(Connection conn = DatabaseConnection.getConnection(); // Estabelece uma conexão com o banco de dados usando o método getConnection da classe DatabaseConnection
        PreparedStatement ps = conn.prepareStatement(sql); // Prepara a consulta SQL para execução no banco de dados, vinculando-a à conexão
        ResultSet rs = ps.executeQuery()){ // Executa a consulta e armazena os resultados no ResultSet
            while(rs.next()){ // Enquanto houver resultados (veiculos) no ResultSet, esse laço será executado
                Veiculo veiculo = new Veiculo(); // Cria um novo objeto Veiculo para cada veículo retornado do banco
                veiculo.setId(rs.getInt("id")); // Atribui o ID do veículo retornado do banco ao objeto Veiculo
                veiculo.setMarca(rs.getString("marca")); // Atribui a marca do veículo retornada do banco ao objeto Veiculo
                veiculo.setModelo(rs.getString("modelo")); // Atribui o modelo do veículo retornado do banco ao objeto Veiculo
                veiculo.setAno(rs.getInt("ano")); // Atribui o ano de fabricação do veículo retornado do banco ao objeto Veiculo
                veiculo.setPlaca(rs.getString("placa")); // Atribui a placa do veículo retornado do banco ao objeto Veiculo
                veiculo.setDiaria(rs.getDouble("preco_por_dia")); // Atribui a diária do veículo retornado do banco ao objeto Veiculo
                veiculo.setCategoria(rs.getString("categoria")); // Atribui a categoria do veículo retornado do banco ao objeto Veiculo
                veiculo.setStatus(rs.getString("status")); // Atribui o status do veículo retornado do banco ao objeto Veiculo
                veiculos.add(veiculo); // Adiciona o objeto Veiculo à lista de veículos
            }
        } catch(SQLException e){ // Se ocorrer um erro durante a execução da consulta SQL, ele será capturado aqui
            e.printStackTrace(); // Imprime o erro no console
        }
        return veiculos; // Retorna a lista de veículos preenchida com os dados do banco
    }

    // Método para atualizar o status de um veículo no banco de dados
    public void atualizarVeiculo(Veiculo veiculo){
        // Declara a consulta SQL que atualiza o status do veículo com base na placa
        String sql = "UPDATE veiculo SET status = ? WHERE placa = ?";
        try(Connection conn = DatabaseConnection.getConnection(); // Estabelece uma conexão com o banco de dados usando o método getConnection da classe DatabaseConnection
        PreparedStatement ps = conn.prepareStatement(sql)){ // Prepara a consulta SQL para execução no banco de dados, vinculando-a à conexão
            ps.setString(1, veiculo.getStatus()); // Define o novo status do veículo
            ps.setString(2, veiculo.getPlaca()); // Define a placa do veículo
            ps.executeUpdate(); // Executa a consulta SQL de atualização
        } catch(SQLException e){ // Se ocorrer um erro durante a execução da consulta SQL, ele será capturado aqui
            e.printStackTrace(); // Imprime o erro no console
        }
    }
}
