package org.example.usecase;

import org.example.dao.VeiculoDAO;
import org.example.dto.VeiculoDTO;
import org.example.model.Veiculo;

import java.util.List;
import java.util.stream.Collectors;

//A classe VeiculoService gerencia a lógica de negócios relacionada aos veículos,
// como cadastrar, buscar, listar e alterar o status de veículos.
public class VeiculoService {
    private VeiculoDAO veiculoDAO; // Declaração de uma instância no DAO para operações no BD

    // Construtor da classe que inicializa o objeto veiculoDAO
    public VeiculoService() {
        this.veiculoDAO = new VeiculoDAO();
    } // Instancia VeiculoDAO para acessar os dados

    // Método para cadastrar um veículo no BD
    // Converte um VeiculoDTO em um objeto Veiculo, define seus atributos e insere o veículo no banco de dados
    public void cadastrarVeiculo(VeiculoDTO veiculoDTO) {

        Veiculo veiculo = new Veiculo(); // Cria um objeto do tipo Veiculo e mapeia os dados via clienteDTO
        veiculo.setMarca(veiculoDTO.getMarca()); // define marca
        veiculo.setModelo(veiculoDTO.getModelo()); // define modelo
        veiculo.setAno(veiculoDTO.getAno()); // define ano de fabricação
        veiculo.setPlaca(veiculoDTO.getPlaca()); // define placa
        veiculo.setDiaria(veiculoDTO.getDiaria()); // define diaria
        veiculo.setCategoria(veiculoDTO.getCategoria()); // define categoria
        veiculo.setStatus("disponível"); // define o status inicial do objeto veículo como "disponível"

        veiculoDAO.inserirVeiculo(veiculo); //Insere o objeto veiculo no BD
    }

    // Método para buscar um veículo no BD por placa e converte o objeto veiculo em veiculoDTO
    // para ser utilizado na aplicação
    public VeiculoDTO buscarVeiculoPorPlaca(String placa) {
        Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca(placa); //Busca o veículo no BD usando o DAO e a placa como parâmetro
        if (veiculo == null) {
            return null; // se a placa não for encontrada, retorna null
        }

        // Cria um objeto veiculoDTO e MAPEIA os dados de veiculo para veiculoDTO (ex: veiculoDTO.setMarca(veiculo.getMarca());
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setMarca(veiculo.getMarca()); // mapeia marca
        veiculoDTO.setModelo(veiculo.getModelo()); // mapeia modelo
        veiculoDTO.setAno(veiculo.getAno()); // mapeia ano
        veiculoDTO.setPlaca(veiculo.getPlaca()); // mapeia placa
        veiculoDTO.setDiaria(veiculo.getDiaria()); // mapeia diaria
        veiculoDTO.setCategoria(veiculo.getCategoria()); // mapeia categoria
        veiculoDTO.setStatus(veiculo.getStatus()); // mapeia status
        return veiculoDTO; // retorno é um objeto do tipo VeiculoDTO
    }

    // Método para listar todos os veículos com status "disponível",
    // convertendo cada um em VeiculoDTO e retornando uma lista de veiculoDTO's
    public List<VeiculoDTO> listarVeiculosDisponiveis() {
        return veiculoDAO.listarVeiculos() // busca todos os veículos
                .stream() // converte a lista em um stream para aplicar o filtro
                .filter(veiculo -> "disponível".equalsIgnoreCase(veiculo.getStatus())) // filtra pelo status "disponível"
                .map(veiculo -> {
                    // Converte e mapeia cada objeto veiculo para um veiculoDTO
                    VeiculoDTO veiculoDTO = new VeiculoDTO();
                    veiculoDTO.setMarca(veiculo.getMarca()); // mapeia marca
                    veiculoDTO.setModelo(veiculo.getModelo()); // mapeia modelo
                    veiculoDTO.setAno(veiculo.getAno()); // mapeia ano
                    veiculoDTO.setPlaca(veiculo.getPlaca()); // mapeia placa
                    veiculoDTO.setDiaria(veiculo.getDiaria()); // mapeia diaria
                    veiculoDTO.setCategoria(veiculo.getCategoria()); // mapeia categoria
                    veiculoDTO.setStatus(veiculo.getStatus()); // mapeia status
                    return veiculoDTO; // retorna um objeto de VeiculoDTO
                })
                .collect(Collectors.toList()); // coleta os objetos VeiculoDTO em uma lista
    }

    // Método para listar todos os veículos com status "alugado",
    // convertendo cada um em VeiculoDTO e retornando uma lista de veiculoDTO's
    public List<VeiculoDTO> listarVeiculosAlugados() {
        return veiculoDAO.listarVeiculos() // busca todos os veículos
                .stream() // converte a lista em um stream para aplicar o filtro
                .filter(veiculo -> "alugado".equalsIgnoreCase(veiculo.getStatus())) // filtra pelo status "alugado"
                .map(veiculo -> {
                    // Converte e MAPEIA cada objeto veiculo para um veiculoDTO
                    VeiculoDTO veiculoDTO = new VeiculoDTO();
                    veiculoDTO.setMarca(veiculo.getMarca()); // mapeia marca
                    veiculoDTO.setModelo(veiculo.getModelo()); // mapeia modelo
                    veiculoDTO.setAno(veiculo.getAno()); // mapeia ano
                    veiculoDTO.setPlaca(veiculo.getPlaca()); // mapeia placa
                    veiculoDTO.setDiaria(veiculo.getDiaria()); // mapeia diaria
                    veiculoDTO.setCategoria(veiculo.getCategoria()); // mapeia categoria
                    veiculoDTO.setStatus(veiculo.getStatus()); // mapeia status
                    return veiculoDTO; // retorna um objeto de VeiculoDTO
                })
                .collect(Collectors.toList()); // coleta os objetos de VeiculoDTO em uma lista
    }

    // Método que busca um veículo pela placa e altera seu status, atualizando no BD
    public void alterarStatus(String placa, String status){
        Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca(placa); // busca o veiculo no BD, utilizando a placa como parâmetro
        if(veiculo != null){
            veiculo.setStatus(status); // se o veiculo é encontrado, atualiza o status
            veiculoDAO.atualizarVeiculo(veiculo); // atualiza o veiculo no BD
        } else {
            System.out.println("Veículo não encontrado."); // exibe mensagem caso o veiculo não seja encontrado
        }
    }
}
