package org.example.usecase;

import org.example.dao.ClienteDAO;
import org.example.dao.EnderecoDAO;
import org.example.dto.ClienteDTO;
import org.example.dto.EnderecoDTO;
import org.example.model.Cliente;
import org.example.model.Endereco;
import org.example.utils.CPFValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

// Classe responsável por gerenciar a lógica de negócio relacionada ao cliente (cadastro e consulta)
public class ClienteService {

    // Declaração dos objetos DAO para operações de banco de dados e formatter para formatação de datas.

    private ClienteDAO clienteDAO; // Responsável pelas operações no banco de dados relacionadas ao cliente
    private EnderecoDAO enderecoDAO; // Responsável pelas operações no banco de dados relacionadas ao endereço
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // formata a data no padrão passado no parâmetro

    // Construtor que inicializa os DAO's
    public ClienteService(){
        this.clienteDAO = new ClienteDAO(); // instancia ClienteDAO
        this.enderecoDAO = new EnderecoDAO(); // instancia EnderecoDAO
    }

    // Método para cadastrar um cliente no sistema
    // Valida (após implementada todas a validações necessárias) os dados do cliente,
    // formata a data de nascimento e insere o cliente e seu endereço no banco de dados.
    public void cadastrarCliente(ClienteDTO clienteDTO){
        if(!CPFValidator.validarCPF(clienteDTO.getCpf())){ // valida o CPF utilizando o CPFValidator. Caso inválido, lança a exceção
            throw new IllegalArgumentException("CPF inválido.");
        }

        if(clienteDTO.getEnderecoDTO() == null){ // verifica se o endereço foi fornecido. Caso contrário, lança exceção
            throw new IllegalArgumentException("Informar o endereço completo.");
        }

        try{
            // tenta converter a data de nascimento do clienteDTO para um objeto do tipo LocalDate, usando o formato definido (formatter)
            LocalDate dataNascimento = LocalDate.parse(clienteDTO.getDataNascimento().format(formatter), formatter);
            clienteDTO.setDataNascimento(dataNascimento); // atualiza clienteDTO com a data formatada
        } catch(DateTimeParseException e){
            // caso o formato da data seja inválido, lança exceção com respectiva mensagem de erro
            throw new IllegalArgumentException("Formato de data válido: dd/mm/aaaa");
        }

        // Cria um novo objeto do tipo Cliente e MAPEIA os dados do clienteDTO para o objeto criado
        // (ex: cliente.setNome(clienteDTO.getNome());
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome()); //mapeia nome
        cliente.setCpf(clienteDTO.getCpf()); // mapeia cpf
        cliente.setDataNascimento(clienteDTO.getDataNascimento()); // mapeia D.N
        cliente.setTelefone(clienteDTO.getTelefone()); // mapeia telefone

        // Cria um novo objeto do tipo Endereco e MAPEIA os dados do enderecoDTO para o objeto criado
        // (ex: endereco.setLogradouro(clienteDTO.getEnderecoDTO().getLogradouro());
        Endereco endereco = new Endereco();
        endereco.setLogradouro(clienteDTO.getEnderecoDTO().getLogradouro()); // mapeia o logradouro
        endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero()); // mapeia o número
        endereco.setBairro(clienteDTO.getEnderecoDTO().getBairro()); // mapeia o bairro
        endereco.setCidade(clienteDTO.getEnderecoDTO().getCidade()); // mapeia a cidade
        endereco.setUf(clienteDTO.getEnderecoDTO().getUf()); // mapeia a UF
        endereco.setCep(clienteDTO.getEnderecoDTO().getCep()); // mapeia o CEP

        // Insere o endereço no BD e obtém o ID gerado
        int enderecoId = enderecoDAO.inserirEndereco(endereco);
        endereco.setId(enderecoId); // atualiza o objeto endereco com o ID gerado
        cliente.setEndereco(endereco); // mapeia o endereço no objeto cliente

        clienteDAO.inserirCliente(cliente); // por fim, insere o cliente no BD
    }

    // Método que busca um cliente no banco a partir do CPF,
    // converte-o para um DTO e retorna um objeto do tipo ClienteDTO
    public ClienteDTO buscarClientePorCPF(String cpf){
        Cliente cliente = clienteDAO.buscarClientePorCPF(cpf); // busca o cliente no BD pelo CPF, usando uma instância de ClienteDAO
        if(cliente == null){
            return null; // se o cliente não for encontrado, retorna null
        }

        // Cria um objeto do tipo ClienteDTO e MAPEIA os dados do cliente para clienteDTO
        // (ex: clienteDTO.setNome(cliente.getNome());)
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome()); // mapeia nome
        clienteDTO.setCpf(cliente.getCpf()); // mapeia cpf
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // cria o objeto para converter a data no padrão desejado
        String dataFormatada = cliente.getDataNascimento().format(formatter); // define a data formatada na varíavel
        clienteDTO.setDataNascimento(LocalDate.parse(dataFormatada, formatter)); // mapeia D.N
        clienteDTO.setTelefone(cliente.getTelefone()); // mapeia telefone

        // Cria um objeto EnderecoDTO e mapeia os dados do endereço do cliente para o DTO.
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(cliente.getEndereco().getLogradouro()); // mapeia logradouro
        enderecoDTO.setNumero(cliente.getEndereco().getNumero()); // mapeia número
        enderecoDTO.setBairro(cliente.getEndereco().getBairro()); // mapeia bairro
        enderecoDTO.setCidade(cliente.getEndereco().getCidade()); // mapeia cidade
        enderecoDTO.setUf(cliente.getEndereco().getUf()); // mapeia uf
        enderecoDTO.setCep(cliente.getEndereco().getCep()); // mapeia cep

        clienteDTO.setEnderecoDTO(enderecoDTO); // associa enderecoDTO ao clienteDTO

        return clienteDTO; // retorna o objeto do tipo ClienteDTO
    }

    // Método que lista todos os clientes do BD, converte cada um para clienteDTO e retorna a lista de clienteDTOs.
    public List<ClienteDTO> listarClientes() {
        return clienteDAO.listarClientes() // usa clienteDAO para listar os clientes
                .stream() // converte a lista para um stream para facilitar a transformação
                .map(cliente -> {
                    // Para cada cliente, cria um clienteDTO e mapeia os dados
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setNome(cliente.getNome()); // mapeia nome
                    clienteDTO.setCpf(cliente.getCpf()); // mapeia cpf
                    clienteDTO.setDataNascimento(cliente.getDataNascimento()); // mapeia D.N
                    clienteDTO.setTelefone(cliente.getTelefone()); // mapeia telefone

                    // Cria um objeto do tipo EnderecoDTO e mapeia os dados de endereco encapsulados em cliente
                    EnderecoDTO enderecoDTO = new EnderecoDTO();
                    enderecoDTO.setLogradouro(cliente.getEndereco().getLogradouro()); // mapeia logradouro
                    enderecoDTO.setNumero(cliente.getEndereco().getNumero()); // mapeia número
                    enderecoDTO.setBairro(cliente.getEndereco().getBairro()); // mapeia bairro
                    enderecoDTO.setCidade(cliente.getEndereco().getCidade()); // mapeia cidade
                    enderecoDTO.setUf(cliente.getEndereco().getUf()); // mapeia uf
                    enderecoDTO.setCep(cliente.getEndereco().getCep()); // mapeia cep

                    clienteDTO.setEnderecoDTO(enderecoDTO); // associa o enderecoDTO ao clienteDTO

                    return clienteDTO; // retorna o objeto do tipo ClienteDTO
                })
                .collect(Collectors.toList()); // coleta os objetos ClienteDTO em uma lista
    }


}
